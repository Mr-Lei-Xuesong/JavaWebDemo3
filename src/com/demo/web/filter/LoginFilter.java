package com.demo.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request= (HttpServletRequest) req;
        //获取访问资源路径
        String uri = request.getRequestURI();
        //判断是否包含登录相关路径
        if (uri.contains("/index.jsp") || uri.contains("/loginServlet") || uri.contains("/checkCodeServlet") || uri.contains("/css/") || uri.contains("/fonts/") || uri.contains("/js/")){
            //包含，放行
            chain.doFilter(req,resp);
        }else {
            //不包含，需要验证用户是否登录
            //从session获取login
            Object login = request.getSession().getAttribute("login");
            if (login!=null){
                chain.doFilter(req,resp);
            }else {
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/index.jsp").forward(request,resp);
            }

        }
        //chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
