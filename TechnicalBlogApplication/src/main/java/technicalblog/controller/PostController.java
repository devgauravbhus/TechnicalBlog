package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Category;
import technicalblog.model.Post;
import technicalblog.model.User;
import technicalblog.services.PostService;

import javax.servlet.http.HttpSession;
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
    public String createPost(Post newPost, Model model, HttpSession session) {
        User user= (User)session.getAttribute("loggeduser");
        newPost.setUser(user);

        if (newPost.getJavaBlog()!=null){
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(newPost.getJavaBlog());
            newPost.getCategories().add(javaBlogCategory);
        }
        if (newPost.getSpringBlog()!=null){
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(newPost.getSpringBlog());
            newPost.getCategories().add(springBlogCategory);
        }
        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name = "postId") Integer postId,Model model){
        Post post = postService.getPost(postId);
        model.addAttribute("post",post);
        return "posts/edit";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name = "postId") Integer postId,Post post){
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name = "postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
