# Logging with elk

## Install elk using docker
```
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.7.0
```

Check Elastic search
```
curl -XGET http://localhost:9200
```

## Install kibana using docker

```
docker run --link YOUR_ELASTICSEARCH_CONTAINER_NAME_OR_ID:elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.7.0
```
Check kibana. 
```
http://localhost:5601/
```

## Install logstash

Download logstash locally from https://www.elastic.co/downloads/logstash
Go to logstash folder, in the config sub folder and add a custom config file
names  ``logstash-custom-config.conf`` with the following content.
```
input {
    tcp {
        port => 4560
        codec => json_lines
    }
}
filter {
    date {
        match => [ "timestamp" , "dd/MMM/yyyy:HH:mm:ss Z" ]
    }
}
output {
  stdout {
    codec => rubydebug
  }
  elasticsearch {
    hosts => ["http://localhost:9200"]
  }
}
```
Turn logstash on from the ``bin`` folder:

 ```./logstash -f ../config/logstash-custom-config.conf```
