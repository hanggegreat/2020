package cn.lollipop.client;

import cn.lollipop.command.*;
import cn.lollipop.common.bean.User;
import cn.lollipop.common.concurrent.FutureTaskScheduler;
import cn.lollipop.sender.ChatSender;
import cn.lollipop.sender.LoginSender;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Data
@Service
public class CommandController {
    /**
     * 聊天命令收集类
     */
    private final ChatConsoleCommand chatConsoleCommand;

    /**
     * 登录命令收集类
     */
    private final LoginConsoleCommand loginConsoleCommand;

    /**
     * 登出命令收集类
     */
    private final LogoutConsoleCommand logoutConsoleCommand;

    /**
     * 菜单命令收集类
     */
    private final ClientCommandMenu clientCommandMenu;

    private Map<String, BaseCommand> commandMap;

    private String menuString;

    /**
     * 会话类
     */
    private ClientSession session;

    private final NettyClient nettyClient;

    private Channel channel;

    private final ChatSender chatSender;

    private final LoginSender loginSender;

    private boolean connectFlag = false;

    private User user;

    private GenericFutureListener<ChannelFuture> closeListener;

    private GenericFutureListener<ChannelFuture> connectedListener;

    public CommandController(ChatConsoleCommand chatConsoleCommand, LoginConsoleCommand loginConsoleCommand, LogoutConsoleCommand logoutConsoleCommand, ClientCommandMenu clientCommandMenu, NettyClient nettyClient, ChatSender chatSender, LoginSender loginSender) {
        this.chatConsoleCommand = chatConsoleCommand;
        this.loginConsoleCommand = loginConsoleCommand;
        this.logoutConsoleCommand = logoutConsoleCommand;
        this.clientCommandMenu = clientCommandMenu;
        this.nettyClient = nettyClient;
        this.chatSender = chatSender;
        this.loginSender = loginSender;
        initConnectedListener();
        initCloseListener();
        initCommandMap();
        startCommandThread();
    }

    private void initConnectedListener() {
        connectedListener = f -> {
            final EventLoop eventLoop = f.channel().eventLoop();
            if (!f.isSuccess()) {
                log.info("连接失败!在10s之后准备尝试重连!");
                eventLoop.schedule(
                        nettyClient::doConnect,
                        10,
                        TimeUnit.SECONDS
                );

                connectFlag = false;
            } else {
                connectFlag = true;

                log.info("IM 服务器 连接成功!");
                channel = f.channel();

                // 创建会话
                session = new ClientSession(channel);
                session.setConnected(true);
                channel.closeFuture().addListener(closeListener);

                //唤醒用户线程
                notifyCommandThread();
            }
        };
    }

    private void initCloseListener() {
        closeListener = f -> {
            log.info(new Date() + ": 连接已经断开……");
            channel = f.channel();

            // 创建会话
            ClientSession session = channel.attr(ClientSession.SESSION_KEY).get();
            session.close();

            //唤醒用户线程
            notifyCommandThread();
        };
    }


    public void initCommandMap() {
        commandMap = new HashMap<>(4);
        commandMap.put(clientCommandMenu.getKey(), clientCommandMenu);
        commandMap.put(chatConsoleCommand.getKey(), chatConsoleCommand);
        commandMap.put(loginConsoleCommand.getKey(), loginConsoleCommand);
        commandMap.put(logoutConsoleCommand.getKey(), logoutConsoleCommand);

        Iterator<Map.Entry<String, BaseCommand>> iterator = commandMap.entrySet().iterator();

        StringBuilder menus = new StringBuilder();
        menus.append("[menu] ");
        while (iterator.hasNext()) {
            BaseCommand next = iterator.next().getValue();

            menus
                    .append(next.getKey())
                    .append("->")
                    .append(next.getTip())
                    .append(" | ");

        }
        menuString = menus.toString();
        clientCommandMenu.setAllCommandsShow(menuString);
    }


    public void startConnectServer() {

        FutureTaskScheduler.add(() -> {
            nettyClient.setConnectedListener(connectedListener);
            nettyClient.doConnect();
        });
    }


    public synchronized void notifyCommandThread() {
        //唤醒，命令收集程
        this.notify();
    }

    public synchronized void waitCommandThread() {
        //休眠，命令收集线程
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startCommandThread() {
        Thread.currentThread().setName("命令线程");

        while (true) {
            //建立连接
            while (!connectFlag) {
                //开始连接
                startConnectServer();
                waitCommandThread();
            }
            //处理命令
            while (null != session) {
                Scanner scanner = new Scanner(System.in);
                clientCommandMenu.exec(scanner);
                String key = clientCommandMenu.getCommandInput();
                BaseCommand command = commandMap.get(key);

                if (null == command) {
                    System.err.println("无法识别指令，请重新输入!");
                    continue;
                }

                switch (key) {
                    case ChatConsoleCommand.KEY:
                        command.exec(scanner);
                        startOneChat((ChatConsoleCommand) command);
                        break;
                    case LoginConsoleCommand.KEY:
                        command.exec(scanner);
                        startLogin((LoginConsoleCommand) command);
                        break;
                    case LogoutConsoleCommand.KEY:
                        command.exec(scanner);
                        startLogout(command);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    //发送单聊消息
    private void startOneChat(ChatConsoleCommand c) {
        //登录
        if (!isLogin()) {
            log.info("还没有登录，请先登录");
            return;
        }
        chatSender.setSession(session);
        chatSender.setUser(user);
        chatSender.sendChatMsg(c.getToUserId(), c.getMessage());

//        waitCommandThread();

    }

    private void startLogin(LoginConsoleCommand command) {
        //登录
        if (!isConnectFlag()) {
            log.info("连接异常，请重新建立连接");
            return;
        }
        User user = new User();
        user.setUid(command.getUserName());
        user.setToken(command.getPassword());
        user.setDevId("1111");
        this.user = user;
        session.setUser(user);
        loginSender.setUser(user);
        loginSender.setSession(session);
        loginSender.sendLoginMsg();
    }


    private void startLogout(BaseCommand command) {
        //登出
        if (!isLogin()) {
            log.info("还没有登录，请先登录");
        }
    }


    public boolean isLogin() {
        if (null == session) {
            log.info("session is null");
            return false;
        }

        return session.isLogin();
    }

}