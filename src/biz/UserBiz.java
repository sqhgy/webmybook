package biz;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

/**
 * @author gaoyao
 * @version JDk1.8
 */
public class UserBiz {
    UserDao userDao = new UserDao() ;
    public User getUser(String name ,String pwd){
    User user = null ;
        try {
            user = userDao.getUser(name,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  user ;
    }
    public int modifyPwd(long id ,String pwd){
        int count = 0 ;
        try {
            count = userDao.modifyPwd(id, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  count ;
    }
}
