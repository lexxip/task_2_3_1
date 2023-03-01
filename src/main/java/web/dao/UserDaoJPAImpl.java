package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class UserDaoJPAImpl implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    public UserDaoJPAImpl() {
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public User showUser(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.id = :id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUser(Long id) {
        entityManager.remove(showUser(id));
    }

    @Override
    public void updateUser(Long id, User user) {
        entityManager.merge(user);
    }

}
