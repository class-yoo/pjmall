package com.example.pjmall.backend.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/*Security Filter Chain
 
 V 표시는 DelegatingFilterProxy에 꼭 설정해야하는 Filter
 
	 1. ChannelProcessingFilter
	 2. SecurityContextPersistenceFilter		( auto-config default 	, V) ACL 없을때 기본적으로 URL 차단해주는역할 ? 
	 3. ConcurrentSessionFilter
	 4. LogoutFilter							( auto-config default	, V)
	 5. UsernamePasswordAuthenticationFilter	( auto-config default 	, V)
	 6. DefaultLoginPageGeneratingFilter		( auto-config default )
	 7. CasAuthenticationFilter
	 8. BasicAuthenticationFilter				( auto-config default )
	 9. RequestCacheAwareFilter					( auto-config default )
	10. SecurityContextHolderAwareRequestFilter	( aut	o-config default )
	11. JaasApiIntegrationFilter
	12. RememberMeAuthenticationFilter			(Custom Filter로 설정해줘야됨 , V)
	13. AnonymousAuthenticationFilter			( auto-config default )
	14. SessionManagementFilter					( auto-config default 	, V)
	15. ExceptionTranslationFilter				( auto-config default 	, V )*/


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	// 스프링 시큐리티 필터 연결
	// WebSecurity 객체
	// SpringSecurityFilterChain 이라는 이름의 DelegatingFilterProxy Bean 객체를 생성
	// DelegatingFilterProxy Bean은 많은 Spring SecurityFilter Chain에 위임한다.
	// DelegatingFilterProxy가 11개의 설정을 가지고있다 ?
	@Override
	public void configure(WebSecurity web) throws Exception {
		 super.configure(web);
	}
	
	// Interceptor URL의 요청을 안전하게 보호(보안)하는 방법
	
	/*
	/user/update  - >(ROLE_USER, ROLE_ADMIN) -> Authenticated
	/user/logout  - >(ROLE_USER, ROLE_ADMIN) -> Authenticated
	/board/write  - >(ROLE_USER, ROLE_ADMIN) -> Authenticated
	/board/delete - >(ROLE_USER, ROLE_ADMIN) -> Authenticated
	/board/modify - >(ROLE_USER, ROLE_ADMIN) -> Authenticated
	/admin/** -> ROLE_ADMIN(Authorized)
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http); //->> 부모클래스가 URL를 차단하고있다.
		
		// 1. ACL 설정
		
		http.authorizeRequests()
		.anyRequest().permitAll();
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
	}
}
