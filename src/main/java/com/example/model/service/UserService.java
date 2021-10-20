package com.example.model.service;

import com.example.model.domain.User;
import com.example.model.dtos.UserDto;
import com.example.model.mapper.UserMapper;
import com.example.model.repository.UserRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(UserDto userDto) {
        User save = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(save);
    }

   public Optional<UserDto> findUser(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDto);
    }

    public Optional<UserDto> findByRefreshToken(String refreshToken) {
        return userRepository.findByToken(refreshToken).map(userMapper::toDto);
    }

    public void saveRefreshToken(String username, String refreshToken) {
        userRepository.findByUsername(username).ifPresent(
                user -> {
                    user.setToken(refreshToken);
                    userRepository.save(user);
                }
        );
    }
}
