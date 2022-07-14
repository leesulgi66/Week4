package com.sparta.mymemo.model;
import com.sparta.mymemo.dto.MemoPostDto;
import com.sparta.mymemo.dto.MemoRequestDelDto;
import com.sparta.mymemo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;


    @Column()
    private String title;

    @Column()
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column()
    private String password;




    //    public Memo(String username, String contents) {
//        this.title = title;
//        this.username = username;
//        this.contents = contents;
//        this.password = password;
//    }
    public Memo(String title, String username, String contents, String password) {
        this.title = title;
        this.username = username;
        this.contents = contents;
        this.password = password;
    }

    public Memo(MemoRequestDto requestDto) {
//        this.title = requestDto.getTitle();
//        this.username = requestDto.getUsername();
//        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public Memo(MemoPostDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public Memo(MemoRequestDelDto requestDto) {
        this.password = requestDto.getPassword();
    }

}