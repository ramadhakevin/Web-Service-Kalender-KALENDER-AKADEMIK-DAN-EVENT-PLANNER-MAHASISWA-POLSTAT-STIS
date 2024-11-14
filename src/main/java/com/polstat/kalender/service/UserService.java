package com.polstat.kalender.service;

import com.polstat.kalender.dto.UserDto;

public interface UserService {
    public UserDto createUser(UserDto user);
    public UserDto viewProfile(Long id);
    public UserDto updateUserProfile(Long id, String name, String email);
    public UserDto updateUserPassword(Long id, String oldPass, String newPass);
    public String deleteUser(Long id);
    public UserDto getUserByEmail(String email);
}
