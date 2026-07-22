package com.praktika.board.console;

import com.praktika.board.entity.User;
import com.praktika.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleUI {

    private final UserService userService;
    private User currentUser = null;
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            if (currentUser == null) {
                showAuthMenu();
            } else {
                showMainMenu();
            }
        }
    }

    private void showAuthMenu() {
        System.out.println("\n=== ДОСКА ОБЪЯВЛЕНИЙ ===");
        System.out.println("1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> login();
            case "2" -> register();
            case "0" -> {
                System.out.println("До свидания!");
                System.exit(0);
            }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void login() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();

        try {
            currentUser = userService.loginUser(username, password);
            System.out.println("Добро пожаловать, " + currentUser.getFullName() + "!");
        } catch (RuntimeException e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
    }

    private void register() {
        System.out.print("Логин: ");
        String username = scanner.nextLine();
        System.out.print("Пароль: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Полное имя: ");
        String fullName = scanner.nextLine();

        try {
            User user = userService.registerUser(username, password, email, fullName);
            System.out.println("Регистрация успешна! Добро пожаловать, " + user.getFullName() + "!");
        } catch (RuntimeException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        }
    }

    private void showMainMenu() {
        System.out.println("\n=== ГЛАВНОЕ МЕНЮ ===");
        System.out.println("1. Просмотреть объявления");
        System.out.println("2. Создать объявление");
        System.out.println("3. Мои объявления");
        System.out.println("0. Выйти из аккаунта");
        System.out.print("Выберите действие: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> System.out.println("Функция в разработке...");
            case "2" -> System.out.println("Функция в разработке...");
            case "3" -> System.out.println("Функция в разработке...");
            case "0" -> {
                currentUser = null;
                System.out.println("Вы вышли из аккаунта.");
            }
            default -> System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}