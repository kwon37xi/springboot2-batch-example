# springboot2-batch-example

# 참조 소스

* [SpringbootBatchExampleApplication.java](https://github.com/kwon37xi/springboot2-batch-example/blob/master/src/main/java/kr/pe/kwonnam/springbootbatchexample/SpringbootBatchExampleApplication.java)
* [application.yml](https://github.com/kwon37xi/springboot2-batch-example/blob/master/src/main/resources/application.properties)

## bootJar
```
./gradlew bootJar
```

`springboot-batch-example-0.0.1-SNAPSHOT.jar` 생성

## run
```
java -jar springboot-batch-example-0.0.1-SNAPSHOT.jar --spring.batch.job.names=helloJob helloParam1=helloValue1 helloParam2=helloValue2

java -jar springboot-batch-example-0.0.1-SNAPSHOT.jar --spring.batch.job.names=worldJob worldParam1=worldValue1 worldParam2=worldValue2

```