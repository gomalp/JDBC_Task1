package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;



public class Main {
    public static UserDao impl= new UserDaoJDBCImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        // запускаем с ключем h имеем Hibernate имплементацию UserDao, по дефолту JDBC
        if (args.length != 0 &&  args[0].equalsIgnoreCase("h"))
            impl = new UserDaoHibernateImpl();


        List<User> listUser = List.of(new User("Иван", "Иванов", (byte) 18),
                    new User("Александр", "Александрович", (byte) 25),
                    new User("Анна", "Федоровна", (byte) 22),
                    new User("Ольга", "Сергеевна", (byte) 21));

        try (UserService service = new UserServiceImpl(impl)){
            service.createUsersTable();
            listUser.forEach(user -> {
                service.saveUser(user.getName(),
                        user.getLastName(),
                        user.getAge());
                System.out.println(String.format("User с именем – %s %s добавлен в базу данных", user.getName(), user.getLastName()));
            });

            System.out.println("\nВсе юзеры");
            for (User user : service.getAllUsers()) {
                System.out.println(user);
            }

            System.out.println("\nудалим всех юзеров");
            service.cleanUsersTable();
            for (User user : service.getAllUsers()) {
                System.out.println(user);
            }

            System.out.println("\nудалим таблицу");
            service.dropUsersTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
