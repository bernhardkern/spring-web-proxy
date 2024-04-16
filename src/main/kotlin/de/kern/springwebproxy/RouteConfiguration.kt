package de.kern.springwebproxy

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.stripPrefix
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.function.RouterFunction
import org.springframework.web.servlet.function.ServerResponse


@Configuration
class RouteConfiguration {

    @Bean
    fun tardisProxyRouterFunction(): RouterFunction<ServerResponse> =
        route()
            .GET("/api/tardis-proxy/**", http("https://icanhazdadjoke.com"))
            .before(stripPrefix(2))
            .after { _, response ->
                //response.headers().clear()
                // response.headers().contentType = MediaType.APPLICATION_JSON
                // response.headers()["content-encoding"] = "br"
                response
            }
            .build()
}