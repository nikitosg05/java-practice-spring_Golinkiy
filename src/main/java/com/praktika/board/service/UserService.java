package com.praktika.board.service;

import com.praktika.board.entity.User;
import com.praktika.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Регистрация пользователя
    public User registerUser(String username, String password, String email, String fullName) {
        // Проверяем, не занят ли логин и почта
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Пользователь с таким логином уже существует");
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Пользователь с такой почтой уже существует");
        }

        // Создаем пользователя
        User user = User.builder()
                .username(username)
                .password(password) 
                .email(email)
                .fullName(fullName)
                .role("USER") // user по умолчанию
                .isBlocked(false) // По умолчанию не заблокирован
                .build();

        return userRepository.save(user);
    }

    // Авторизация
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Неверный пароль");
        }

        if (user.getIsBlocked()) {
            throw new RuntimeException("Пользователь заблокирован");
        }

        return user;
    }

    // Поиск пользователя по логину
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }
}