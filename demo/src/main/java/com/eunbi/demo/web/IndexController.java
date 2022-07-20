package com.eunbi.demo.web;

import com.eunbi.demo.config.auth.LoginUser;
import com.eunbi.demo.config.auth.dto.SessionUser;
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

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
// mustache 맵핑
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    // postsService.findAllDesc() 로 가져온 결과를 posts 로 index.mustache 에 전달
    public String index(Model model, @LoginUser SessionUser user){
    // public String index(Model model){ // 어노테이션으로 중복되는 코드
        model.addAttribute("posts", postsService.findAllDesc());

        // userName 을 사용할 수 있게 IndexController 에서 userName 을 model 에 저장하는 코드 추가
        // CustomOAuth2Userservice 에서 로그인 성공 시 세션에 SessionUser 저장하도록 구성
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 세션에 저장된 값 있을 때만 model 로 userName 등록
        // 저장된 값이 없으면 로그인 버튼이 보인다
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
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
