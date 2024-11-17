package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;



public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        List<User> listUser=List.of(new User("Иван","Иванов", (byte) 18),
                                    new User("Александр","Александрович",(byte) 25),
                                    new User("Анна","Федоровна",(byte) 22),
                                    new User("Ольга","Сергеевна",(byte) 21));

        UserService service=new UserServiceImpl();

        service.createUsersTable();
        listUser.forEach(user->{service.saveUser(user.getName(),
                                                user.getLastName(),
                                                user.getAge());
                                System.out.println(String.format("User с именем – %s %s добавлен в базу данных",user.getName(),user.getLastName()));
        });

        System.out.println("\nВсе юзеры");
        for(User user: service.getAllUsers()){
            System.out.println(user);
        }

        System.out.println("\nудалим всех юзеров");
        service.cleanUsersTable();
        for(User user: service.getAllUsers()){
            System.out.println(user);
        }

        System.out.println("\nудалим таблицу");
        service.dropUsersTable();
        }

}
