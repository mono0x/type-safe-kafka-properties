package net.mono0x.test.kafka.properties

import java.util.*

data class KafkaConsumerProperties(
        val a: Int
) {
    fun toProperties(): Properties {
        return Properties().also {

        }
    }
}
