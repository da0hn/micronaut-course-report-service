package dev.da0hn.course.micronaut.consumer

import dev.da0hn.course.micronaut.domain.Sale
import dev.da0hn.course.micronaut.repository.SaleRepository
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.messaging.annotation.MessageBody
import io.micronaut.serde.ObjectMapper

@KafkaListener(groupId = "report-service-consumer", clientId = "sales", threads = 5, offsetReset = OffsetReset.EARLIEST)
class SaleConsumer(
  private val saleService: SaleRepository,
  private val objectMapper: ObjectMapper,
) {


  @Topic("sales-topic")
  fun receiveSale(@KafkaKey id: String, @MessageBody json: String) {
    val sale = this.objectMapper.readValue(json, Sale::class.java)
    val result = saleService.create(sale)
    println("Sale created: ${result.insertedId}")
  }
}
