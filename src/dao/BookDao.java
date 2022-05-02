package dao;


import bean.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author gaoyao
 * @version JDk1.8
 */
public class BookDao {
    QueryRunner runner = new QueryRunner() ;


    public List<Book> getBooksByTypeId(long typeId) throws SQLException {
        Connection conn = DBHelper.getConnection() ;
        String sql = "select *from book where typeId = ?" ;
        List<Book> books = runner.query(conn,sql,new BeanListHandler<Book>(Book.class),typeId);
        conn.close();
        return  books ;
    }

    public static void main(String[] args) {
        try {
            List<Book> books = new BookDao().getBooksByTypeId(2);
            System.out.println(books.size());//[]
            for (Book book :books){
                System.out.println(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
