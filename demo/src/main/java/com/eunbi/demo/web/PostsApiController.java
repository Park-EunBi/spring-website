package com.eunbi.demo.web;

import com.eunbi.demo.service.posts.PostsService;
import com.eunbi.demo.web.dto.PostsResponseDto;
import com.eunbi.demo.web.dto.PostsSaveRequestsDto;
import com.eunbi.demo.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 외부 요청, 응답에 대한 전반적인 처리
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    // 등록
    @PostMapping("api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDto requestDto) {
        return postsService.save(requestDto);
    }

    // 수정
    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // 조회
    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
