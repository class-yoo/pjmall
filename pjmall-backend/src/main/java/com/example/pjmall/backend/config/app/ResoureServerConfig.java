package com.example.pjmall.backend.config.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;


@Configuration
@EnableResourceServer
public class ResoureServerConfig extends ResourceServerConfiguration {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
//		지원 서버 접근 권한 설정
		
		http
		.authorizeRequests()
		.antMatchers("/hello")
		.access("#oauth2.hasScope('read')");
	}
	

}
