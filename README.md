# springboot2-batch-example

명령행으로 Spring Boot 2 에서 Spring Batch Job을 실행시킬 수 있게 하는 예제.

이와 같이 설정하면 `org.springframework.boot.autoconfigure.batch.JobLauncherCommandLineRunner`가 활성화 되면서 
명령행으로 Job을 실행하고, 배치 파라미터까지 지정할 수 있게 된다.

 
## 참조 소스
* http://kwonnam.pe.kr/wiki/springframework/springboot/batch
* [SpringbootBatchExampleApplication.java](https://github.com/kwon37xi/springboot2-batch-example/blob/master/src/main/java/kr/pe/kwonnam/springbootbatchexample/SpringbootBatchExampleApplication.java)
* [application.yml](https://github.com/kwon37xi/springboot2-batch-example/blob/master/src/main/resources/application.properties)

## bootJar
```
./gradlew bootJar
```

`springboot-batch-example-0.0.1-SNAPSHOT.jar` 생성

## run
* `--spring.batch.job.names=yourJobName` : 실행할 batch job 을 명시한다. 이를 명시하지 않으면 오류를 발생시킨다.
* `param1=value1 param2=value2 ...` : 원하는 배치 Job Parameter 를 애플리케이션 파라미터로 지정할 수 있다.

```
java -jar springboot-batch-example-0.0.1-SNAPSHOT.jar --spring.batch.job.names=helloJob helloParam1=helloValue1 helloParam2=helloValue2

java -jar springboot-batch-example-0.0.1-SNAPSHOT.jar --spring.batch.job.names=worldJob worldParam1=worldValue1 worldParam2=worldValue2

```