package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public void setCarUser(Car car, User user) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE Car car set car.user.id = :userId WHERE car.id = :carId")
                .setParameter("userId", user.getId())
                .setParameter("carId", car.getId())
                .executeUpdate();
    }

    @Override
    public List<Car> listCars() {
        return null;
    }
}
