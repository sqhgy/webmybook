package dao;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author gaoyao
 * @version JDk1.8
 */
public class UserDao {

    QueryRunner runner = new QueryRunner();
    public User getUser(String name ,String pwd) throws SQLException {

        Connection conn = DBHelper.getConnection();

        String sql = "SELECT * FROM `user` WHERE name = ? and pwd = ? and state = 1 " ;


        User user = runner.query(conn, sql, new BeanHandler<User>(User.class),name,pwd);


        conn.close();


        return user ;

    }
    //修改密码
    public int modifyPwd(long id ,String pwd) throws SQLException {

    String sql = "UPDATE `user` SET pwd = ? WHERE id =? " ;
    Connection conn = DBHelper.getConnection();
       int count = runner.update(conn, sql, pwd, id);
        conn.close();
        return count;

    }

    public static void main(String[] args) {
        User user = null;
        try {
            user = new UserDao().getUser("admin","123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(user);
    }
}
