package com.sparta.mymemo.service;

import com.sparta.mymemo.model.Memo;
import com.sparta.mymemo.dto.MemoRequestDto;
import com.sparta.mymemo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long update(long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        memo.update(requestDto);
        return id;
    }
}
