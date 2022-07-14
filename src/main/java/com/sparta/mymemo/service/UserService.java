package com.sparta.mymemo.service;

import com.sparta.mymemo.dto.SignupRequestDto;
import com.sparta.mymemo.model.User;
import com.sparta.mymemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        String passwordCheck = requestDto.getPasswordCheck();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        //정규식
        String usernameConfim = "^[a-zA-Z0-9]{3,}$";
        String passwordConfim = "^[a-zA-Z0-9]{4,}$";


        if (found.isPresent()) {
            return "중복된 사용자 ID가 존재합니다.";
        }
        //패스워드 재 확인
        if (!password.equals(passwordCheck)) {
            return "패스 워드가 다릅니다.";
        }
        //패스워드 속 아이디 확인
        if (password.contains(username)) {
            return "비밀번호에 아이디가 포함 될 수 없습니다.";
        }
        //아이디 조건
        boolean usernameResult = Pattern.matches(usernameConfim, username);
        if(!usernameResult){
            return "닉네임을 최소 3자 이상, 알파벳 대소문자 및 숫자로 설정해주세요.";
        }
        //패스워드 조건
        boolean passResult = Pattern.matches(passwordConfim, password);
        if (!passResult){
            return "패스워드는 최소 4자 이상, 알파벳 대소문자 및 숫자로 설정해주세요.";
        }

        // 패스워드 암호화
        password = passwordEncoder.encode(requestDto.getPassword());
//
        User user = new User(username, password);
        userRepository.save(user);
        return "회원가입 성공";
    }

}