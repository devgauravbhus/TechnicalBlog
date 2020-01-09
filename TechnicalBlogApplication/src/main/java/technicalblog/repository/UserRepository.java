package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public void registerUser(User newUser){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(newUser);
            et.commit();
        }catch (Exception e){
            et.rollback();
        }
    }

    public User checkUser(String username, String password){
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<User> typedQuery = em.createQuery("SELECT u from User u where u.username= :username AND u.password = :password", User.class);
            typedQuery.setParameter("username",username);
            typedQuery.setParameter("password",password);

            return typedQuery.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }

    }
}
