package net.mono0x.test.kafka.properties

import org.apache.kafka.clients.producer.ProducerConfig
import java.util.*

data class KafkaProducerProperties(
        val acks: Int,
        val bootstrapServers: String
) {
    fun toProperties(): Properties {
        return Properties().also {
            it[ProducerConfig.ACKS_CONFIG] = acks
            it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        }
    }
}
