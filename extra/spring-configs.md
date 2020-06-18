Disable eureka
```
eureka:
  client:
    enabled: false
```

Add property to test
```
@SpringBootTest(properties = "foo=bar")
```

Or active profile
```
@ActiveProfils("test")
```
