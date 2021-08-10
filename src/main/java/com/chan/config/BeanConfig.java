package com.chan.config;

import com.chan.dto.TotalsDto;
import com.chan.pojo.BlogsAndTags;
import com.chan.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author bronchan
 * @ClassName BeanConfig
 * @date 2021/8/5 16:22
 * @Version 1.0
 * @Description TODO
 */
@Configuration
public class BeanConfig {

    @Bean
    public BlogsAndTags blogsAndTags(){
        return new BlogsAndTags();
    }

    @Bean
    public TotalsDto totalsDto(){
        return new TotalsDto();
    }

    @Bean
    public User user(){
        return new User();
    }
}
