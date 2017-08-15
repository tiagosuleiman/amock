package com.mock.security;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@PropertySource({ "classpath:persistence-jndi.properties" })
@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private Environment env;
	
	@Autowired
	private ClientDetailsService clientService;
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		
/*		String usersQuery = "select username, password, enabled from users where username=?";
		String authority = "select username, role from roles where username=?";
		
		auth.jdbcAuthentication()
		.withDefaultSchema()
		.dataSource(dataSource())
		.usersByUsernameQuery(usersQuery)
		.authoritiesByUsernameQuery(authority)
		.passwordEncoder(passwordEncoder());*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.anonymous().disable()
	  	.authorizeRequests()
	  	.antMatchers("/oauth/token").permitAll();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientService));
		handler.setClientDetailsService(clientService);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
	
	
    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }
	
    @Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
