package zhth.bom.management.bom.handlerinterceptor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName="myFilter",urlPatterns={"/","/css","/js","/lib","/index"},asyncSupported=true)
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
               //TODO
                HttpServletRequest r=(HttpServletRequest)request;
                HttpSession session=r.getSession();
                if(session.getAttribute("user")!=null){
                        chain.doFilter(request,response);
                }else {
                    request.getRequestDispatcher("/").forward(request,response);
                }
    }

    @Override
    public void destroy() {

    }
}
