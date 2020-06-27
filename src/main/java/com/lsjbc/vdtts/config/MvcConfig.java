package com.lsjbc.vdtts.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ClassName: MvcConfig
 * @Description: 关于MVC的一些控制类
 * @Datetime: 2020/6/6   23:26
 * @Author: JX181114 - 郑建辉
 */
@Configuration
public class MvcConfig {


    /**
     * 解决Whitelabel Error Page的问题
     * 注意事项：
     *      1：这里的所有路径会在以下4个目录中查找
     *          A：/resources/META-INF/resources
     *          B：/resources/resources
     *          C：/resources/static
     *          D:/resources/public
     *      2：不会进入templates路径查找
     *      3：这里检测到异常会在控制台打印错误信息(重要)
     *      4：如果下方有设置就会在/4xx和/5xx的路径会在{注意事项1}中的四个路径或四个路径下的error目录中查找
     *      5：如果没有设置就会在templates的error路径中查找(最好不设置)
     * @return
     * @author JX181114 --- 郑建辉
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return factory -> {
            factory.addErrorPages(
                    //new ErrorPage(异常类型.class, 跳转界面)
                    //404页面
                    new ErrorPage(HttpStatus.NOT_FOUND, "/404.html")
                    //500页面
                    ,new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html")
            );
        };
    }
}
