package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface CarDao {

    void add(Car car);

    void setCarUser(Car car, User user);

    List<Car> listCars();

}


