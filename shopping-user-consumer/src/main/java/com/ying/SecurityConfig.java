package com.ying;

import com.ying.service.lmpl.UserDetailsServiceImpl;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	DataSource dataSource;//引入Druid连接池。

	@Autowired
	private UserDetailsServiceImpl tUsersDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	//  密码需要设置编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		//BCryptPasswordEncoder就是接口PasswordEncoder其中实现类
		//BCrypt代表使用B-Tree->B树算法加密和解密。-》Base64算法/Hash哈希算法等等。
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 1、使用内存用户信息，作为测试使用
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)//验证添加编码和解码器（加密工具）
//                .withUser("guest")//添加用户名
//                .password(passwordEncoder.encode("123")).roles("")//添加密码，同时对密码进行加密
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder.encode("111")).roles("");

		//        // 2、使用JDBC进行身份认证
        //验证用户名和密码
        /*String userSQL ="select user_name,user_password,user_status from sys_user where user_name=?";
        //验证权限
        String authoritySQL="select u.user_name,r.role_name from sys_user u inner join sys_user_role ur on u.user_id=ur.userid \n" +
                "inner join sys_role r on ur.roleid=r.role_id\n" +
                "where u.user_name=?";*/
		/*auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder)//添加编码器
                .dataSource(dataSource)//引入连接池Druid
                .usersByUsernameQuery(userSQL)//必须按用户名查询用户
                .authoritiesByUsernameQuery(authoritySQL);//必须按用户名查询用户角色*/
		//密码需要设置编译器
		//3.使用UserDetailsService进行身份认证
		auth.userDetailsService(tUsersDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("登录");
		//解决非thymeleaf的form表单提交被拦截问题
		http.csrf().disable();

		// 2、自定义用户登录控制
		http.formLogin()
				.loginPage("/userLogin")
				.usernameParameter("name").passwordParameter("pwd")
				.defaultSuccessUrl("/login")
				.failureUrl("/userLogin?error=true");

		// 1、自定义用户访问控制
		http.authorizeRequests()
				/*.antMatchers("/","/page/**","/article/**","/login","/index.do","/userLogin").permitAll()
				.antMatchers("/back/**","/assets/**","/user/**","/article_img/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")*/
				/*.antMatchers("/").permitAll()*/
				.antMatchers("/comm/**","/client/**","/article/**","/login").permitAll()
				.antMatchers("/back/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin();                //允许表单登陆
		//退出删除cookie
		http.logout()
				.permitAll()
				.logoutUrl("/mylogout")  //执行注销的url
				.invalidateHttpSession(true) // 指定是否在注销时让httpSession无效
				.deleteCookies("JESSIONID")  // 清除cookie
				.logoutSuccessUrl("/userLogin"); // 注销成功后跳转的url

		/*// 定制Remember-me记住我功能
		//<input type="checkbox" name="rememberme"> 记住我
		http.rememberMe()
				.rememberMeParameter("rememberme") //接收复选框的值
				.tokenValiditySeconds(200);//利用Cookie保存用户信息200秒.



		//// 5、针对访问无权限页面出现的403页面进行定制处理
		http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
				// 如果是权限访问异常，则进行拦截到指定错误页面
				RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/errorPage/comm/error_403");
				dispatcher.forward(httpServletRequest, httpServletResponse);
			}});*/

	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		//解决静态资源被拦截的问题
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/img/**");
		web.ignoring().antMatchers("/js/**");
		web.ignoring().antMatchers("/register");
	}
}
