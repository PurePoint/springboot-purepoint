package com.purepoint.springbootpurepoint.user.service;

import java.util.Optional;

import com.purepoint.springbootpurepoint.user.domain.User;

/**
 * UserService 인터페이스는 사용자 관련 서비스 메서드를 정의합니다.
 */
public interface UserService {

    /**
     * 사용자를 로그인합니다.
     *
     * @param username 사용자의 사용자명
     * @param password 사용자의 비밀번호
     * @return 로그인된 사용자의 정보를 담은 Optional 객체
     */
    Optional<User> loginUser(String username, String password);

    /**
     * 새로운 사용자를 생성합니다. (회원가입)
     *
     * @param user 생성할 사용자 정보
     * @return 생성된 사용자 정보
     */
    User createUser(User user);

    /**
     * 사용자의 프로필 사진을 수정합니다.
     *
     * @param userId 프로필 사진을 수정할 사용자의 ID
     * @param profilePictureUrl 새로운 프로필 사진 URL
     * @return 수정된 사용자 정보
     */
    User updateUserProfilePicture(Long userId, String profilePictureUrl);

    /**
     * 사용자의 닉네임을 수정합니다.
     *
     * @param userId 닉네임을 수정할 사용자의 ID
     * @param nickname 새로운 닉네임
     * @return 수정된 사용자 정보
     */
    User updateUserNickname(Long userId, String nickname);

    /**
     * 사용자의 비밀번호를 수정합니다.
     *
     * @param userId 비밀번호를 수정할 사용자의 ID
     * @param newPassword 새로운 비밀번호
     * @return 수정된 사용자 정보
     */
    User updateUserPassword(Long userId, String newPassword);

    /**
     * 사용자의 정보를 조회합니다.
     *
     * @param userId 조회할 사용자의 ID
     * @return 조회된 사용자의 정보를 담은 Optional 객체
     */
    Optional<User> getUserById(Long userId);

    /**
     * 사용자를 삭제합니다. (회원탈퇴)
     *
     * @param userId 삭제할 사용자의 ID
     */
    void deleteUser(Long userId);
}
