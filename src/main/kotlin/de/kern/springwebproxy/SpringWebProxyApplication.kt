package de.kern.springwebproxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebProxyApplication

fun main(args: Array<String>) {
    runApplication<SpringWebProxyApplication>(*args)
}
