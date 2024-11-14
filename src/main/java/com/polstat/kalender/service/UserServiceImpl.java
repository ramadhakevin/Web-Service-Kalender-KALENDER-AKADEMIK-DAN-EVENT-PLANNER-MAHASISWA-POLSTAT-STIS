package com.polstat.kalender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polstat.kalender.dto.UserDto;
import com.polstat.kalender.entity.User;
import com.polstat.kalender.exception.InvalidPasswordException;
import com.polstat.kalender.exception.UserNotFoundException;
import com.polstat.kalender.mapper.UserMapper;
import com.polstat.kalender.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUserProfile(Long id, String name, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUserPassword(Long id, String oldPass, String newPass) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!passwordEncoder.matches(oldPass, user.getPassword())) {
            throw new InvalidPasswordException("Incorrect old password");
        }

        String encryptedPassword = passwordEncoder.encode(newPass);
        user.setPassword(encryptedPassword);
        user = userRepository.save(user);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            userRepository.delete(user);
            return "User ID " + id + " has been deleted.";
        } else {
            return "User ID " + id + " not found.";
        }
    }

    @Override
    public UserDto viewProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserMapper.mapToUserDto(user);
    }
}
