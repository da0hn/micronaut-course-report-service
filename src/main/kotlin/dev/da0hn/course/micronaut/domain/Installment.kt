package dev.da0hn.course.micronaut.domain

import io.micronaut.serde.annotation.Serdeable
import java.math.BigDecimal
import java.time.LocalDate

@Serdeable
data class Installment(
  val number: Int,
  val value: BigDecimal,
  val dueDate: LocalDate,
)

typealias Installments = MutableList<Installment>
