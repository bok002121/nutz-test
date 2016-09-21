package net.cyc.nutzbook.mvc;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.NutFilter;

public class NutzBookNutFilter extends NutFilter {

    protected Set<String> prefixs = new HashSet<String>();


    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        prefixs.add(conf.getServletContext().getContextPath() + "/druid/");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs/");
        prefixs.add(conf.getServletContext().getContextPath() + "/jsp/user");
        prefixs.add(conf.getServletContext().getContextPath() + "/index.jsp");
        //System.out.println(conf.getServletContext().getContextPath() + "/jsp/user");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        // 判断用户是否登录
//        HttpServletRequest r = (HttpServletRequest)req;
//        HttpSession session = r.getSession();
//        
//        if(null == session.getAttribute("me"))
//		{
//			(( HttpServletResponse )resp).sendRedirect("index.jsp");
//			return ;
//		}
//        System.out.println("session get");

        // 在这里判断用户权限！阻止那些用户夸权访问
        
        
        
        super.doFilter(req, resp, chain);
    }
}