package com.example;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.DispatcherServlet;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan
@MapperScan("com.example.mapper")//扫描com.example.mapper包下的类

public class DemoApplication {
	/**
	 * 修改DispatcherServlet默认配置
	 */
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
		registration.getUrlMappings().clear();
		registration.addUrlMappings("*.do"); //只有*.do 的请求能通过
		registration.addUrlMappings("*.json");
		return registration;
	}

	//DataSource配置
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	//提供SqlSeesion
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));//映射文件是resource/mybatis/目录下所有.xml文件

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
