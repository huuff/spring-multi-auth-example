package xyz.haff.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import xyz.haff.auth.config.Issuers

@SpringBootApplication
@EnableConfigurationProperties(Issuers::class)
class SpringMultiAuthExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringMultiAuthExampleApplication>(*args)
}
