package com.greentran.springsecuritycsdn.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//读取数据库判断
/*        SysUser sysUser = sysUserService.queryByUsername(username);

        if (Objects.nonNull(sysUser)) {
            return User.withUsername(username).password(sysUser.getEncodePassword())
                    .authorities(AuthorityUtils.NO_AUTHORITIES)
                    .build();
        }
        throw new UsernameNotFoundException("username: " + username + " notfound");*/


        logger.info("登录用户名：" + username);
        String passWord=passwordEncoder().encode("111");
        logger.info("密码：" + passWord);
        return new User(username,passWord, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        //页面默认会对密码加密，数据库里如果在用户注册时，用的是加密过的密码，则直接读取比较即可
        //return new User(username,"$2a$10$YFZDTqyBqwHkV/vTxKrhtuyIQCMD/joeIylCs8wbvXnhOYRgD/kDq", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}