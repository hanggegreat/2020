package cn.lollipop;

import cn.lollipop.client.CommandController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientApplication.class, args);
        CommandController commandClient = context.getBean(CommandController.class);

        try {
            commandClient.initCommandMap();
            commandClient.startCommandThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}