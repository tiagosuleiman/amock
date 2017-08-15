package com.mock.security;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@PropertySource({ "classpath:persistence-jndi.properties" })
@Configuration
@EnableAuthorizationServer
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {

	//private static String REALM="EXAMPLE_REALM";
	
	@Autowired
    private Environment env;
	
	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private UserApprovalHandler handler;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		/*clients.inMemory()
	        .withClient("myRestClient") // client id
            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
            .scopes("read", "write", "trust")
            .secret("P@ssw0rd")
            .accessTokenValiditySeconds(120).//invalid after 2 minutes.
            refreshTokenValiditySeconds(600);//refresh after 10 minutes.
		*/
		
		clients.jdbc(dataSource())
        .withClient("myRestClient") // client id
	        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
	        .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
	        .scopes("read", "write", "trust")
	        .secret("P@ssw0rd")
	        .redirectUris("http://localhost:3333/sispar")
	        .accessTokenValiditySeconds(120) //invalid after 2 minutes.
	        .refreshTokenValiditySeconds(600)//refresh after 10 minutes.
		.and() 
	    	.withClient("my-client-with-registered-redirect") 
	    	.authorizedGrantTypes("authorization_code") 
	    	.authorities("ROLE_CLIENT") 
	    	.scopes("read", "trust") 
	    	.resourceIds("oauth2-resource") 
	    	.redirectUris("http://anywhere?key=value") 
	    .and() 
	    	.withClient("my-client-with-secret") 
	    	.authorizedGrantTypes("client_credentials", "password") 
	    	.authorities("ROLE_CLIENT") 
	    	.scopes("read") 
	    	.resourceIds("oauth2-resource") 
	    	.secret("secret"); 
		
	}
	
	
    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }
    
/*    
    @Bean
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
     
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));
        return dataSource;
    }*/

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
	    .tokenStore(tokenStore)
	    .userApprovalHandler(handler)
		.authenticationManager(authManager);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		//oauthServer.realm(REALM+"/client");
		 oauthServer
         .tokenKeyAccess("permitAll()")
         .checkTokenAccess("isAuthenticated()");
	}

	  @Bean
	    public TokenStore tokenStore() throws NamingException {
	        return new JdbcTokenStore(dataSource());
	    }
}