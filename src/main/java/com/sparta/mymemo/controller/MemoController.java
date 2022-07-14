package com.sparta.mymemo.controller;

import com.sparta.mymemo.dto.MemoPostDto;
import com.sparta.mymemo.model.Memo;
import com.sparta.mymemo.repository.MemoRepository;
import com.sparta.mymemo.dto.MemoRequestDelDto;
import com.sparta.mymemo.dto.MemoRequestDto;
import com.sparta.mymemo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;


    @GetMapping("/api/memos") // 게시글 조회
    public List<Memo> readMemo() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/memos") //게시글 생성
    public Memo createMemo(@RequestBody MemoPostDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @PutMapping("/api/memos/{id}") //게시글 수정
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 아이디가 없습니다.")
        );
        memo.update(requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}") //게시글 삭제
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }

    @GetMapping("/api/memos/{id}") //특정 게시글 조회
    public Memo findMemo(@PathVariable long id){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        return memo;
    }

}
