package dev.da0hn.course.micronaut.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.result.InsertOneResult
import dev.da0hn.course.micronaut.domain.Sale
import jakarta.inject.Singleton

@Singleton
class SaleRepository(
  private val mongoClient: MongoClient
) {

  fun create(sale: Sale): InsertOneResult = getConnection().insertOne(sale)

  private fun getConnection(): MongoCollection<Sale> = mongoClient.getDatabase("sales-db").getCollection("sales", Sale::class.java)


}
