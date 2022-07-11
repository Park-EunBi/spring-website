package com.eunbi.demo.service.posts;

import com.eunbi.demo.domain.posts.PostsRepository;
import com.eunbi.demo.web.dto.PostsSaveRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 트랜잭션이 사용되는 곳
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public long save(PostsSaveRequestsDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
