## Dependency
```
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <version>5.3</version>
    <exclusions>
        <exclusion>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### Logback configuration
logback.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <include resource="org/springframework/boot/logging/logback/base.xml"/>

    <appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <remoteHost>localhost</remoteHost>

        <encoder class="net.logstash.logback.encoder.LogstashEncoder">
            <customFields>{"app_name":"inventory-service", "environment":"localhost"}</customFields>
        </encoder>
    </appender>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="net.logstash.logback.encoder.LogstashEncoder">
            <customFields>{"app_name":"inventory-service", "environment":"localhost"}
            </customFields>
        </encoder>
    </appender>

    <logger name="org.apache.kafka" level="ERROR"/>

    <root level="INFO">
        <appender-ref ref="LOGSTASH"/>
        <appender-ref ref="CONSOLE"/>
    </root>

</configuration>
```
