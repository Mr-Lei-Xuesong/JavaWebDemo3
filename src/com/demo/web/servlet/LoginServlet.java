package com.demo.web.servlet;

import com.demo.dao.AdminDao;
import com.demo.dao.impl.AdminDaoImpl;
import com.demo.domain.Admin;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取参数map集合
        Map<String, String[]> map = request.getParameterMap();
        Admin me = new Admin();
        try {
            BeanUtils.populate(me, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //获取生成的验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //获取完就删除验证码
        session.removeAttribute("CHECKCODE_SERVER");
        //判断验证码是否正确
        if (checkcode_server != null && checkcode_server.equalsIgnoreCase(me.getVerifycode())) {//忽略大小写
            AdminDao ad = new AdminDaoImpl();
            Admin login = ad.login(me);
            if (login != null) {//登录成功
                //将用户信息存入session
                session.setAttribute("login", login);
                response.sendRedirect(request.getContextPath() + "/findUserByPageServlet");
            } else {//登录失败
                //提示信息
                request.setAttribute("login_msg", "用户名或密码错误");
                //跳转页面
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            //验证码不一致，存储提示信息到request
            request.setAttribute("login_msg", "验证码错误!");
            //转发到登录页面
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
