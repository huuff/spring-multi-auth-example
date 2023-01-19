package xyz.haff.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringMultiAuthExampleApplication

fun main(args: Array<String>) {
    runApplication<SpringMultiAuthExampleApplication>(*args)
}
