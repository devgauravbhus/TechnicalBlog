package technicalblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
@Autowired
private PostRepository repository;
    public List<Post> getAllPosts() throws SQLException {
//        Post post = new Post();
//        post.setTitle("Post 1");
//        post.setBody("Post Body 1");
//        post.setDate(new Date());
//
//        Post post2 = new Post();
//        post2.setTitle("Post 2");
//        post2.setBody("Post Body 2");
//        post2.setDate(new Date());
//
//        Post post3 = new Post();
//        post3.setTitle("Post 3");
//        post3.setBody("Post Body 3");
//        post3.setDate(new Date());

//        ArrayList<Post> posts=new ArrayList<>();
//        posts.add(post);
//        posts.add(post2);
//        posts.add(post3);
//        Connection connection = null;
//        try{
//            Class.forName("org.postgresql.Driver");
//
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "Imy42~*HII");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM posts");
//            while(rs.next()){
//                Post post = new Post();
//                post.setTitle(rs.getString("title"));
//                post.setBody(rs.getString("body"));
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            connection.close();
//        }
        return repository.getAllPosts();
    }
    public Post getOnePost() {
//        ArrayList<Post> posts = new ArrayList<>();
//        Connection connection = null;
//        try{
//            Class.forName("org.postgresql.Driver");
//
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalblog","postgres", "Imy42~*HII");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM posts order by id desc limit 1");
//            while(rs.next()){
//                Post post = new Post();
//                post.setTitle(rs.getString("title"));
//                post.setBody(rs.getString("body"));
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }finally {
//            connection.close();
//        }

        return repository.getLatestPost();

    }

    public void createPost(Post post1) {
        post1.setDate(new Date());
        repository.createPost(post1);
        System.out.println("new post : "+post1);
    }
    public Post getPost(Integer postId){
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost){
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }
    public void deletePost(Integer postId){
        repository.deletePost(postId);
    }
}
