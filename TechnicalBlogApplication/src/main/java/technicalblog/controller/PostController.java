package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.services.PostService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPosts(Model model) throws SQLException {
        List<Post> posts = postService.getAllPosts();
//        posts.add(postService.getOnePost());
        model.addAttribute("posts", posts);
        return "posts";
    }
    @RequestMapping("/posts/newpost")
    public String newPost() {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPost(Post newPost,Model model) {
        postService.createPost(newPost);
        return "redirect:/posts";
    }
}
