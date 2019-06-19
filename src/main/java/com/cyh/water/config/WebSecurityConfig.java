package com.cyh.water.config;

import com.cyh.water.Interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor getHandlerInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludeUrl = new ArrayList<>();  //排除拦截的url
        excludeUrl.add("loginUser");  //登陆
        excludeUrl.add("createImageCode");  //创建登陆验证码
        registry.addInterceptor(getHandlerInterceptor())
                .excludePathPatterns(excludeUrl)
                .addPathPatterns("/**");  //拦截所有
    }
}
