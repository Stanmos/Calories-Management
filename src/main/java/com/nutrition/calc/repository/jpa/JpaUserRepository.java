package com.nutrition.calc.repository.jpa;

import com.nutrition.calc.model.User;
import com.nutrition.calc.repository.UserRepository;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    /*
    @Autowired
    private SessionFactory sessionFactory;
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager em;

    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public boolean delete(int id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);
*/
        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        List<User> users = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1", User.class)
                .setParameter(1, email)
                .getResultList();
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.name, u.email", User.class)
                .getResultList();
    }
}
