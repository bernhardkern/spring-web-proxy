This repository demonstrates an issue of IntelliJ Ultimate http-client

The header validation of IntelliJ http client is too strict, compared to `httpie` or `curl`

Requirements:
JVM 21
Gradle 8

To reproduce the issue: 

1. Start the application SpringWebProxyApplication using `./gradlew bootRun` or `gradle bootRun` with local gradle installation.

2. call _with_curl.sh -> it works to receive a response
3. call _with_httpie.sh -> it works to receive a response
4. call _with_gateway.http (http-client)

-> java.lang.IllegalArgumentException: empty headers are not allowed []

Obviously the header validation is much stricter compared to curl and httpie and the whole request fails:

```
java.lang.IllegalArgumentException: empty headers are not allowed []
at io.netty.handler.codec.http.DefaultHttpHeadersFactory$1.validateName(DefaultHttpHeadersFactory.java:34)
at io.netty.handler.codec.http.DefaultHttpHeadersFactory$1.validateName(DefaultHttpHeadersFactory.java:30)
at io.netty.handler.codec.DefaultHeaders.validateName(DefaultHeaders.java:1012)
at io.netty.handler.codec.DefaultHeaders.add(DefaultHeaders.java:329)
at io.netty.handler.codec.DefaultHeaders.addObject(DefaultHeaders.java:364)
at io.netty.handler.codec.http.DefaultHttpHeaders.add(DefaultHttpHeaders.java:182)
at io.netty.handler.codec.http.HttpObjectDecoder.readHeaders(HttpObjectDecoder.java:725)
at io.netty.handler.codec.http.HttpObjectDecoder.decode(HttpObjectDecoder.java:359)
at io.netty.handler.codec.http.HttpClientCodec$Decoder.decode(HttpClientCodec.java:320)
at io.netty.handler.codec.ByteToMessageDecoder.decodeRemovalReentryProtection(ByteToMessageDecoder.java:529)
at io.netty.handler.codec.ByteToMessageDecoder.callDecode(ByteToMessageDecoder.java:468)
at io.netty.handler.codec.ByteToMessageDecoder.channelRead(ByteToMessageDecoder.java:290)
at io.netty.channel.CombinedChannelDuplexHandler.channelRead(CombinedChannelDuplexHandler.java:251)
at io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(AbstractChannelHandlerContext.java:442)
```