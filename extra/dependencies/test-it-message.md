BrandConsumerSpecIT ----> consumer
```
ExternalChannel kafkaChannels
Message message =   MessageBuilder
                .withPayload(paylaod)
                .build()
kafkaChannels.<channelName>().send(message)
```

EventPublisherServiceSpecIT -----> producer
```
MessageCollector messageCollector
VoucherChannels source
BlockingQueue<Message> events = messageCollector.forChannel(source.vouchers())
events.poll().getPayload() ---> check payload after using message gateway
```
