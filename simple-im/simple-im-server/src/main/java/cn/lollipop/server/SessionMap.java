package cn.lollipop.server;

import cn.lollipop.common.bean.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * session map
 *
 * @author zhangyuanhang
 */
@Slf4j
@Data
public final class SessionMap {
    private SessionMap() {
    }

    private static SessionMap singleInstance = new SessionMap();

    /**
     * 会话集合
     */
    private ConcurrentHashMap<String, ServerSession> map = new ConcurrentHashMap<>();

    public static SessionMap inst() {
        return singleInstance;
    }

    /**
     * 增加session对象
     */
    public void addSession(ServerSession session) {
        map.put(session.getSessionId(), session);
        log.info("用户登录:id= " + session.getUser().getUid() + "   在线总数: " + map.size());
    }

    /**
     * 获取session对象
     */
    public ServerSession getSession(String sessionId) {
        return map.getOrDefault(sessionId, null);
    }

    /**
     * 根据用户id，获取session对象
     */
    public List<ServerSession> getSessionsBy(String userId) {
        return map.values()
                .stream()
                .filter(s -> s.getUser().getUid().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * 删除session
     */
    public void removeSession(String sessionId) {
        if (!map.containsKey(sessionId)) {
            return;
        }

        ServerSession s = map.get(sessionId);
        map.remove(sessionId);
        log.info("用户下线:id= " + s.getUser().getUid() + "   在线总数: " + map.size());
    }

    public boolean hasLogin(User user) {
        for (Map.Entry<String, ServerSession> next : map.entrySet()) {
            User u = next.getValue().getUser();
            if (u.getUid().equals(user.getUid()) && u.getPlatform().equals(user.getPlatform())) {
                return true;
            }
        }

        return false;
    }
}