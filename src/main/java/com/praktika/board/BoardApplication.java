package com.praktika.board;

import com.praktika.board.console.ConsoleUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BoardApplication.class, args);
        ConsoleUI consoleUI = context.getBean(ConsoleUI.class);
        consoleUI.start();
    }
}