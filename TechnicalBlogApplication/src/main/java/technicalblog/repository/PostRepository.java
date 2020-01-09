package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPosts() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
        List<Post> resultList = query.getResultList();
        return resultList;
    }
    public Post getLatestPost(){
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class,2);
    }

    public Post createPost(Post newPost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(newPost);
            et.commit();
        }catch (Exception e){
            et.rollback();
        }
        return newPost;
    }

    public Post getPost(Integer postId){
        EntityManager em = emf.createEntityManager();
        return em.find(Post.class,postId);
    }

    public void updatePost(Post updatedPost){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(updatedPost);
            et.commit();
        }catch (Exception e){
            et.rollback();
        }
    }
    public void deletePost(Integer postId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Post post = em.find(Post.class,postId);
            em.remove(post);
            et.commit();
        }catch (Exception e){
            et.rollback();
        }
    }
}
