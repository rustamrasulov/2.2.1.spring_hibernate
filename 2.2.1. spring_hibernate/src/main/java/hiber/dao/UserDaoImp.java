package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User user WHERE user.car.model = :model AND user.car.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series);
        return query.getSingleResult();
    }

    @Override
    public User getUserByCar(Car car) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("FROM User user WHERE user.id = :userCarId", User.class)
                .setParameter("userCarId", car.getUser().getId());
        return query.getSingleResult();
    }

}


