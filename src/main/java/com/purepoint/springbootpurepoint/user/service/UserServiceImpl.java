package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.domain.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> login(String username, String password) {
        return Optional.empty();
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUserProfilePicture(Long userId, String profilePictureUrl) {
        return null;
    }

    @Override
    public User updateUserNickname(Long userId, String nickname) {
        return null;
    }

    @Override
    public User updateUserPassword(Long userId, String newPassword) {
        return null;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {

    }
}
