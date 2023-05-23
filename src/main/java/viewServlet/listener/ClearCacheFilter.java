package viewServlet.listener;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Xenqiao
 * @create 2023/5/21 17:11
 *
 * 这是一个 NB哄哄的过滤器，用来在 Tomcat启动时
 * 自动清空浏览器缓存，更新 .js文件内容
 */
public class ClearCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        httpServletResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
        httpServletResponse.setHeader("Expires", "0"); // Proxies
        chain.doFilter(request, response);
        System.out.println("NB哄哄的过滤器被执行了，这真的是泰酷辣");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
