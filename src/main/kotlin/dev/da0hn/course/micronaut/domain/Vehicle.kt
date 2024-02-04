package dev.da0hn.course.micronaut.domain

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Vehicle(
  val id: Long,
  val model: String,
  val brand: String,
  val year: Int,
)
