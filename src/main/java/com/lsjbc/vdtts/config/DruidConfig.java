package com.lsjbc.vdtts.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DruidConfig
 * @Description: 配置Druid连接池
 * @Datetime: 2020/6/5   15:09
 * @Author: JX181114 - 郑建辉
 */
@Configuration
public class DruidConfig {

    /**
     * 根据配置文件中的属性来构造一个连接池对象
     *
     * @return DataSource
     * @author JX181114 --- 郑建辉
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置一个Druid的后台管理的Servlet
     *
     * @return ServletRegistrationBean
     * @author JX181114 --- 郑建辉
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {

        //设置访问路径
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //配置初始化参数
        Map<String, String> initParam = new HashMap<>(4);
        //配置查看监控的用户名
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        //配置查看监控的密码
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "jx1910");
        //允许访问的IP地址  不填都可以访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        //不允许访问的IP地址   不填就不禁止
        initParam.put(StatViewServlet.PARAM_NAME_DENY, "");

        //设置初始化参数
        bean.setInitParameters(initParam);

        return bean;
    }

    /**
     * 配置一个Druid的过滤器
     *
     * @return FilterRegistrationBean
     * @author JX181114 --- 郑建辉
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //配置初始化参数
        Map<String, String> initParam = new HashMap<>();
        //设置所有不拦截的请求
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        //设置初始化参数
        bean.setInitParameters(initParam);
        //设置拦截请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
