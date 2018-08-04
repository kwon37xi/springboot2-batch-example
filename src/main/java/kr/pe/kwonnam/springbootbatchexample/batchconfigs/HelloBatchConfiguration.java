package kr.pe.kwonnam.springbootbatchexample.batchconfigs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
// 아래를 통해 실행 대상 job으로 들어갔을 때만 해당 Job 설정이 수행되게 하면 불필요한 설정 부담을 줄일 수 있고
// 실수로 job이 실행되는 것을 막을 수 있다.
@ConditionalOnProperty(prefix = "spring.batch.job", name = "names", havingValue = "helloJob")
public class HelloBatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    public HelloBatchConfiguration() {
        // job.names가 helloJob 일 때만 생성자의 아래 로그가 출력됨.
        log.info("======== Hello Job configured!! ==========");
    }

    @Bean(name = "helloJob")
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("taskletStep").tasklet(new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("==== Hello Job Tasklet called....");
                Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
                // 모든 Job Parameter 출력
                jobParameters.forEach((key, value) -> {
                    log.info("key : {}, value : {}", key, value);
                });
                return RepeatStatus.FINISHED;
            }
        }).build();
    }
}
