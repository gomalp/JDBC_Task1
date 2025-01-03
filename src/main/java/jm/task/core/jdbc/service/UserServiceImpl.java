package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    //private final UserDao userDao = new UserDaoJDBCImpl();
    private final UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoJDBCImpl();
    }
    public UserServiceImpl(UserDao impl){
        if(impl instanceof UserDaoHibernateImpl) userDao = new UserDaoHibernateImpl();
        else userDao=impl;
        System.out.println("UserServiceImpl(UserDao impl) invoked   userDao="+userDao);
    }
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public void close() {
        if(userDao instanceof UserDaoHibernateImpl){
            Util.getSessionFactory().close();
            System.out.println("SessionFactory закрыт");
        }
    }
}
