package dev.da0hn.course.micronaut

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import jakarta.inject.Inject

@MicronautTest
class ReportServiceTest {

  @Inject
  lateinit var application: EmbeddedApplication<*>

  @Test
  fun testItWorks() {
    Assertions.assertTrue(application.isRunning)
  }

}
