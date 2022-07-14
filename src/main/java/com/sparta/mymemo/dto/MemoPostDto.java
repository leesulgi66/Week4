package com.sparta.mymemo.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor //기본 생성자
public class MemoPostDto {
    private String contents;
    private String username;
}
