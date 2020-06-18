```
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

#### Urls
- /actuator/health
- /actuator/info

Enable all endpoints:
```
management.endpoints.web.exposure.include=*
```

See metrics
```
http://localhost:8080/actuator/health
http://localhost:8080/actuator/info
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/hikaricp.connections.usage
```


