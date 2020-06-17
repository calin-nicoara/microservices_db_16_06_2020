## Curl operations

Getting token with **password** grant:
```
curl  -XPOST -k http://localhost:9999/oauth/token \
   -u clientId:secret -d grant_type=password \
   -d username=user -d password=pass
```

Getting token with **client credentials** grant:

```
curl  -XPOST -k http://localhost:9999/oauth/token \
   -u clientId:secret -d grant_type=client_credentials 
```
