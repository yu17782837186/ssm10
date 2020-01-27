package com.ning.servlet;

import com.ning.pojo.Users;
import com.ning.service.UsersService;
import com.ning.service.impl.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UsersService usersService;
    @Override
    public void init() throws ServletException {
        ApplicationContext ac  = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        usersService=ac.getBean("usersService",UsersServiceImpl.class);
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String code = request.getParameter("code");
        String codeSession = request.getSession().getAttribute("code").toString();
        if(codeSession.equals(code)){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Users users = new Users();
            users.setPassword(password);
            users.setUsername(username);
            Users user = usersService.login(users);
            if(user!=null){
                response.sendRedirect("main.jsp");
            }else{
                request.setAttribute("error", "用户名密码不正确");
                request.getRequestDispatcher("/show.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("error", "验证码不正确");
            request.getRequestDispatcher("/show.jsp").forward(request, response);
        }

    }
}
