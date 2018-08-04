package kr.pe.kwonnam.springbootbatchexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class BatchDataSourceConfiguration {
    @Bean
    public DataSource batchDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
//                .addScript("classpath:/org/springframework/batch/core/schema-h2.sql") // spring.batch.initialize-schema=embedded 에 의해 자동 초기화
                .build();
    }
}
