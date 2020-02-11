package net.mono0x.test.kafka

import net.mono0x.test.kafka.properties.KafkaProducerProperties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.kafka.test.rule.EmbeddedKafkaRule
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
class EmbeddedKafkaProducerTest {
    companion object {
        @ClassRule
        @JvmField
        val embeddedKafkaRule = EmbeddedKafkaRule(1)
    }

    @Test
    fun test() {
        val broker = embeddedKafkaRule.embeddedKafka.also {
            it.addTopics("topic")
        }

        val producerProperties = KafkaProducerProperties(
                acks = 0,
                bootstrapServers = broker.brokersAsString
        )

        val stringSerializer = StringSerializer()
        KafkaProducer<String, String>(
                producerProperties.toProperties(),
                stringSerializer,
                stringSerializer
        ).use { producer ->
            val metadata = producer.send(ProducerRecord("topic", "key", "value")).get()
            assertThat(metadata).isNotNull
        }
    }
}
