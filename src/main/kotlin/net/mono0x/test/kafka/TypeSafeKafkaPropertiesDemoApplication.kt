package net.mono0x.test.kafka

import org.springframework.boot.SpringApplication

object TypeSafeKafkaPropertiesDemoApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        SpringApplication.run(TypeSafeKafkaPropertiesDemoApplication::class.java, *args)
    }
}
