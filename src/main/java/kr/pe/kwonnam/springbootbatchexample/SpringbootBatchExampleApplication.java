package kr.pe.kwonnam.springbootbatchexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * {@code spring.batch.job.enabled=true} 일 경우 자동으로 {@link JobLauncherCommandLineRunner} 가 생성되어
 * {@code spring.batch.job.names=jobName1,jobName2} 로 명시된 Job을 실행해준다.
 * <p>
 * {@code spring.batch.job.names}를 명시하지 않으면 <em>모든 job</em>를 실행해버리기 때문에 이에 대해 방어를 해야한다.
 * <p>
 * <code>
 * java kr.pe.kwonnam.springbootbatchexample.SpringbootBatchExampleApplication -Dspring.batch.job.names=helloJob param1=valueBlah1 param2=valueBlah2
 * </code>
 *
 * @see BatchAutoConfiguration
 */
@Slf4j
@EnableBatchProcessing
@SpringBootApplication
public class SpringbootBatchExampleApplication extends DefaultBatchConfigurer {

    public static void main(String[] args) {
        int exitCode = SpringApplication.exit(SpringApplication.run(SpringbootBatchExampleApplication.class, args));
        System.exit(exitCode);
    }

    @Value("${spring.batch.job.names:NONE}")
    private String jobNames;

    // 어디선가 미리 생성해둔 spring batch 용 database
    @Autowired
    @Override
    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
        super.setDataSource(batchDataSource);
    }

    // spring.batch.job.names 를 지정하지 않으면 모든 Job이 실행돼 버리기 때문에
    // 방어차원에서 넣은 job.names validation 처리
    @PostConstruct
    public void validateJobNames() {
        log.info("jobNames : {}", jobNames);
        if (jobNames.isEmpty() || jobNames.equals("NONE")) {
            throw new IllegalStateException("spring.batch.job.names=job1,job2 형태로 실행을 원하는 Job을 명시해야만 합니다!");
        }
    }
}
