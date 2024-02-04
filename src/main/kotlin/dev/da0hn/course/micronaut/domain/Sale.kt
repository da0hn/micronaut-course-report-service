package dev.da0hn.course.micronaut.domain

import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal

@Serdeable
data class Sale(
  val client: String,
  val vehicle: Vehicle,
  val price: BigDecimal,
  val installmentsQuantity: Int,
  val installments: List<Installment>,
)
