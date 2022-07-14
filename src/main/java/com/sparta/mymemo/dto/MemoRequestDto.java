package com.sparta.mymemo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor //기본 생성자
public class MemoRequestDto {
    private final String title;
    private final String username;
    private final String password;
    private final String contents;
}
