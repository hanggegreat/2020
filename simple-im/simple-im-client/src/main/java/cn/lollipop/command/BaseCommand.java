package cn.lollipop.command;

import java.util.Scanner;

/**
 * @author lollipop
 * @date 2020/11/2
 */
public interface BaseCommand {
    void exec(Scanner scanner);

    String getKey();

    String getTip();
}
