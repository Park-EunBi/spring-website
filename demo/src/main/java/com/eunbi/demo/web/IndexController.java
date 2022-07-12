package com.eunbi.demo.web;

import com.eunbi.demo.service.posts.PostsService;
import com.eunbi.demo.web.dto.PostsResponseDto;
import com.eunbi.demo.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
