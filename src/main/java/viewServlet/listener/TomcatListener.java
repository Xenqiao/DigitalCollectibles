package viewServlet.listener;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import util.contractRealize.FiscoInitializer;

/**
 * @author Xenqiao
 * @create 2023/5/20 21:17
 *
 * 这个类用于在 tomcat10 启动时初始化 Fisco 链
 */
@WebListener
public class TomcatListener implements ServletContextListener {
    public FiscoInitializer fiscoInitializer;
    @Override
    public void contextInitialized(ServletContextEvent sre) {
        fiscoInitializer = FiscoInitializer.theGetBcosSDK();


        //过滤器清除浏览器缓存
        FilterRegistration.Dynamic filter = sre.getServletContext().addFilter("ClearCacheFilter", ClearCacheFilter.class);
        // *.js 换成 /* 就是更新所有文件
        filter.addMappingForUrlPatterns(null, true, "*.js","*.html");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sre) {
        //tomcat 停止时确保 fisco 断开
        fiscoInitializer.stopBcosSDK();

    }

}
