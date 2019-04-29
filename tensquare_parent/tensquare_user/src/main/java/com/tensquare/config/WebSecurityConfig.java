package com.tensquare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 配置安全策略
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    /**
     * 我们在添加了Spring Security的依赖后,学过的小伙伴应该都知道,所有的地址都被控制了,
     * 而这里我们只是想用这个算法,所以我们需要一个配置类,配置所有地址都可以匿名访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
        * authorizeRequests
          所有security安全配置的开端,表示开始说明需要的权限,必须这么写
         * 需要的权限两部分:第一部分四拦截的路径,第二部分访问路径需要的权限
         * antMatchers表示拦截所有路径,
        * 所有的意思,permitAll任何权限都可以访问
         * anyRequest任何请求,authenticated认证后才能访问
         *  and().csrf().disable()固定写法,表示csrf拦截失效
         * */
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
