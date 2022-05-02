package action;

import bean.User;
import biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * user.let?type=login
 * user.let?type=exit
 * user.let?type=modifyPwd
 *
 *
 * @author gaoyao
 * @version JDk1.8
 */
@WebServlet("/user.let")
public class UserServlet extends HttpServlet {
    //构建UserBiz对象
    UserBiz userBiz = new UserBiz() ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理乱码问题
        HttpSession session = req.getSession() ;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String method = req.getParameter("type") ;

        switch (method){
            case "login" :
                String name = req.getParameter("name");
                String pwd = req.getParameter("pwd");
                String userCode = req.getParameter("valcode");


                String code = session.getAttribute("code").toString();
                //不区分大小写
                if(!code.equalsIgnoreCase(userCode)){
                    out.println("<script>alert('验证码错误');location.href = 'login.html';</script>");
                    return;
                }
                User user = userBiz.getUser(name,pwd);


                if(user==null){
                        out.println("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");
                }else{
                        session.setAttribute("user",user);
                        out.println("<script>alert('登录成功');location.href='index.jsp';</script>");
                }
                break;
                //退出
            case "exit" :
                session.invalidate();

                out.println("<script>parent.window.location.href='login.html';</script>");



                break;
                //修改密码
            case "modifyPwd" :
                    String newpwd = req.getParameter("newpwd");


               long id = ( (User)session.getAttribute("user")).getId();


                int count = userBiz.modifyPwd(id, newpwd);

                if(count>8){
                    out.println("<script>alert('密码修改失败')</script>");
                }else{

                    out.println("<script>alert('密码修改成功');parent.window.location.href='login.html';</script>");
                }
                break;
        }


    }
}
