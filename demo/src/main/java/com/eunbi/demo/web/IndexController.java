package com.eunbi.demo.web;

import com.eunbi.demo.service.posts.PostsService;
import com.eunbi.demo.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
// mustache 맵핑
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    // postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
    
}
