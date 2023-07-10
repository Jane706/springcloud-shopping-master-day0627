package com.ying;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity//打开SpringSecurity验证和权限控制开关。
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	private DataSource dataSource;

	//  密码需要设置编码器
	@Autowired
	private PasswordEncoder passwordEncoder;
	//  密码需要设置编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 1、使用内存用户信息，作为测试使用
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
//                .withUser("guest")
//                .password(passwordEncoder.encode("123")).roles("")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("111")).roles("");

		//        // 2、使用JDBC进行身份认证
		//验证用户名和密码
		String userSQL ="select username,userpassword,userstatus from tbs_user where username=?";
		//验证权限
		String authoritySQL="select u.username,r.rolename " +
				"from tbs_user u inner join tbs_user_role ur on u.userid = ur.userid " +
				"inner join tbs_role r on ur.roleid =  r.roleid where u.username=?";

		auth.jdbcAuthentication()
				.passwordEncoder(passwordEncoder)
				.dataSource(dataSource)
				.usersByUsernameQuery(userSQL)
				.authoritiesByUsernameQuery(authoritySQL);
	}

	/** 放行静态资源 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		//解决静态资源被拦截的问题
		web.ignoring().antMatchers("/**");

	}

}
