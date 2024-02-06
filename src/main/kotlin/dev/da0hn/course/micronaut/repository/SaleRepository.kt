package dev.da0hn.course.micronaut.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.result.InsertOneResult
import dev.da0hn.course.micronaut.domain.Installment
import dev.da0hn.course.micronaut.domain.Sale
import dev.da0hn.course.micronaut.domain.Vehicle
import jakarta.inject.Singleton
import org.bson.Document
import org.bson.types.Decimal128
import java.time.ZoneId
import java.util.Date

@Singleton
class SaleRepository(
  private val mongoClient: MongoClient,
) {

  fun create(sale: Sale): InsertOneResult = getConnection().insertOne(sale)

  fun findAll(): List<Sale> {
    return this.mongoClient.getDatabase("sales-db").getCollection("sales", Document::class.java).find().toList()
      .map { document ->
        val vehicle = getVehicle(document.get("vehicle", Document::class.java)!!)
        val installments = getInstallments(document)
        Sale(
          document.getString("client"),
          vehicle,
          document.get("price", Decimal128::class.java).bigDecimalValue(),
          document.getInteger("installmentsQuantity"),
          installments,
        )
      }
  }

  private fun getVehicle(vehicleDocument: Document): Vehicle {
    return Vehicle(
      vehicleDocument.getLong("_id"),
      vehicleDocument.getString("model"),
      vehicleDocument.getString("brand"),
      vehicleDocument.getInteger("year"),
    )
  }

  private fun getInstallments(document: Document) = document.getList("installments", Document::class.java)
    .map { installmentsDocument ->
      Installment(
        installmentsDocument.getInteger("number"),
        installmentsDocument.get("value", Decimal128::class.java).bigDecimalValue(),
        installmentsDocument.get("dueDate", Date::class.java).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
      )
    }

  private fun getConnection(): MongoCollection<Sale> {
    return mongoClient.getDatabase("sales-db").getCollection("sales", Sale::class.java)
  }


}
