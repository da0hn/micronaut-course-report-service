package dev.da0hn.course.micronaut.controller

import dev.da0hn.course.micronaut.repository.SaleRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/sales-report")
class SaleReportController(
  private val saleRepository: SaleRepository
) {

  @Get
  fun getSalesReport() = this.saleRepository.findAll()

}
