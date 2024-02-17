package com.hackerspace2024.webservice.web;

import com.hackerspace2024.webservice.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class WebController {

    private PostsService postsService;

    @GetMapping("/")
    public String mainDo(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "main";
    }
}
