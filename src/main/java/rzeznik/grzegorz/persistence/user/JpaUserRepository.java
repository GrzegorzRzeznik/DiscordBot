package rzeznik.grzegorz.persistence.user;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import static rzeznik.grzegorz.persistence.util.EntityManagerProvider.getEntityManager;

public class JpaUserRepository implements UserRepository{

    @Override
    public boolean isConnectionValid() {
        EntityManager em = getEntityManager();
        return em.isOpen();
    }

    @Override
    public List<User> findAll() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("FROM User");
        return (List<User>) query.getResultList();
    }

    @Override
    public void save(User user) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void update(User user){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public Optional<User> findByFamilyName(String familyName) {
        try{
            EntityManager em = getEntityManager();
            Query query = em.createQuery("FROM User u WHERE u.familyName = :familyName");
            query.setParameter("familyName", familyName);
            User user = (User)query.getSingleResult();
            em.detach(user);
            return Optional.of(user);
        }catch (Exception e ){
            System.err.println("Error, couldn't find user: " + e.getLocalizedMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteByFamilyName(String familyName) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("DELETE FROM User u WHERE u.familyName = :familyName");
        query.setParameter("familyName", familyName);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<User> findByClass(CharacterClass characterClass) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("FROM User u WHERE u.characterClass = :characterClass");
        query.setParameter("characterClass", characterClass);
        return (List<User>) query.getResultList();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        try{
            EntityManager em = getEntityManager();
            User user = em.find(User.class, userId);
            em.detach(user);
            return Optional.of(user);
        }catch (Exception e ){
            System.err.println("Error, couldn't find user: " + e.getLocalizedMessage());
            return Optional.empty();
        }

    }
}
