package net.mono0x.test.kafka.properties

import org.apache.kafka.streams.StreamsConfig
import java.util.*

data class KafkaStreamsProperties(
        val applicationId: String,
        val stateDir: String,
        val consumerProperties: KafkaConsumerProperties,
        val producerProperties: KafkaProducerProperties?
) {
    fun toProperties(): Properties {
        return Properties().also {
            it[StreamsConfig.APPLICATION_ID_CONFIG] = applicationId
            it[StreamsConfig.STATE_DIR_CONFIG] = stateDir

            it.putAll(consumerProperties.toProperties()
                    .mapKeys { e -> StreamsConfig.consumerPrefix(e.key as String) })

            if (producerProperties != null) {
                it.putAll(producerProperties.toProperties()
                        .mapKeys { e -> StreamsConfig.producerPrefix(e.key as String) })
            }
        }
    }
}
