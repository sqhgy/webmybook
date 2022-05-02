package biz;

import bean.Book;
import bean.Type;
import dao.BookDao;
import dao.TypeDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author gaoyao
 * @version JDk1.8
 */
public class TypeBiz {

    TypeDao typeDao = new TypeDao() ;
    public List<Type> getAll(){
        try {
            return typeDao.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return  null ;
        }
    }

    public int add (String name , long parentId){
        int count = 0 ;
        try {
            int add = typeDao.add(name, parentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count ;
    }
    public int modify(long id , String name ,long parentId){
        int count = 0 ;
        try {
            int add = typeDao.modify(id, name, parentId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count ;

    }
    //删除
    public int remove(long id) throws Exception {

        BookDao bookDao = new BookDao() ;
        int count = 0 ;
        try {
            List<Book> books = bookDao.getBooksByTypeId(id);

            if (books.size()>0){
                throw new Exception("删除类型有子信息，删除失败");
            }
            count = typeDao.remove(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count ;
    }

    public Type getById(long id){
        try {
            return typeDao.getById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
