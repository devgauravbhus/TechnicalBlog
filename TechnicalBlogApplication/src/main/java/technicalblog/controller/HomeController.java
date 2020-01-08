package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import technicalblog.model.Post;
import technicalblog.services.PostService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String getAllPost(Model model){
        List<Post> posts= null;
        try {
            posts = postService.getAllPosts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("posts",posts);
        return "index";
    }
}
