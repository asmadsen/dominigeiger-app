package no.kristiania.dominigeiger

import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MicroMeterConfig {

    @Bean()
    fun getMeterRegistry(): MeterRegistry = SimpleMeterRegistry()
}