# springboot2-batch-example

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