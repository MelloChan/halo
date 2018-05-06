package com.halo;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author MelloChan
 * @date 2018/5/2
 */
@SpringBootApplication
public class HaloServerApplication {
	private static ImmutableMap<String, String> errorCodeMap = null;
	private final Environment env;

	static {
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("error_code.properties");
			errorCodeMap = Maps.fromProperties(properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	public HaloServerApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(HaloServerApplication.class, args);
	}

	@Bean
	@Primary
	public DataSource dataSource(){
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.initialSize")));
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.maxActive")));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.minIdle")));
		dataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.maxWait")));
		dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(env.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(env.getProperty("spring.datasource.validationQuery"));
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(true);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
		dataSource.setUseGlobalDataSourceStat(true);
		dataSource.setConnectionProperties(env.getProperty("spring.datasource.connectionProperties"));
		return dataSource;
	}

	@Bean
	public ImmutableMap<String, String> errorCodeMap() {
		return errorCodeMap;
	}
}
