package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        User user1 = new User("Vasya", "Pupkin", "vasya.pupkin@mail.ru");
        User user2 = new User("Mikael", "Shumaher", "mk@fritz.de");

        Car car1 = new Car("Mercedes", 1980);
        Car car2 = new Car("Volvo", 2021);

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2);

        carService.add(car2);
        carService.setCarUser(car2, user2);

        for (User user : userService.listUsers()) {
            System.out.println("Id = " + user.getId()
                    + " | First Name = " + user.getFirstName()
                    + " | Last Name = " + user.getLastName()
                    + " | Email = " + user.getEmail());
        }

        System.out.println("\nUsers with car");
        for (User usersWithCar : userService.listUsers()) {
            if (usersWithCar.getCar() != null) {
                System.out.println(usersWithCar.getFirstName() + " " +
                        usersWithCar.getLastName());
            }
        }

        System.out.println("\nFinding user by car");
        User userWithCar = userService.getUserByCar("Mercedes", 1980);
        System.out.println("Id = " + userWithCar.getId()
                + " | First Name = " + userWithCar.getFirstName()
                + " | Last Name = " + userWithCar.getLastName()
                + " | Email = " + userWithCar.getEmail()
                + " | Car = " + userWithCar.getCar().getModel());

        System.out.println("\nFinding user by car");
        User userWithCar2 = userService.getUserByCar(car2.getModel(), car2.getSeries());
        System.out.println("Id = " + userWithCar2.getId()
                + " | First Name = " + userWithCar2.getFirstName()
                + " | Last Name = " + userWithCar2.getLastName()
                + " | Email = " + userWithCar2.getEmail()
                + " | Car = " + userWithCar2.getCar().getModel());

        context.close();
    }
}
