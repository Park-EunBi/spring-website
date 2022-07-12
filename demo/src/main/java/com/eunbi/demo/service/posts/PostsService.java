package com.eunbi.demo.service.posts;

import com.eunbi.demo.domain.posts.Posts;
import com.eunbi.demo.domain.posts.PostsRepository;
import com.eunbi.demo.web.dto.PostsListResponseDto;
import com.eunbi.demo.web.dto.PostsResponseDto;
import com.eunbi.demo.web.dto.PostsSaveRequestsDto;
import com.eunbi.demo.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 트랜잭션이 사용되는 곳
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestsDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    // 트랜잭션 범위를 유지하며 조회 기능만 남겨두는 옵션
    // 조회 속도 개선 -> 등록, 수정, 삭제가 없을 때 추천
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                // .map(posts -> nw PostListResponseDto(posts)) 을 람다식으로 변형
                .collect(Collectors.toList());
    }
}
