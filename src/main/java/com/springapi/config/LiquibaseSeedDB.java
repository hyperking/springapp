// package com.springapi.config;

// import liquibase.integration.spring.SpringLiquibase;
// import org.springframework.context.annotation.Bean;
// // import java.util.ArrayList;
// // import java.util.List;
// // import java.util.Map;
// import javax.sql.DataSource;

// /**
// * LiquibaseSeedDB
// */
// public class LiquibaseSeedDB {
// private DataSource dataSource;

// @Bean
// public SpringLiquibase liquibase() {
// SpringLiquibase liquibase = new SpringLiquibase();
// // liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
// liquibase.setDataSource(dataSource);
// return liquibase;
// }

// }