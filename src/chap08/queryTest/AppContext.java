package chap08.queryTest;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
	
	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(2);
		ds.setMinIdle(2);
		ds.setMaxIdle(5);
		
		return ds;
	}
	
	@Bean
	public MemberCountBean memberCount() {
		return new MemberCountBean();
	}
}
