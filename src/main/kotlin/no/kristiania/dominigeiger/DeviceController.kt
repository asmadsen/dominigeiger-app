package no.kristiania.dominigeiger

import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.MeterRegistry
import no.kristiania.dominigeiger.db.Device
import no.kristiania.dominigeiger.db.DeviceMeasurement
import no.kristiania.dominigeiger.db.DeviceMeasurementRepository
import no.kristiania.dominigeiger.db.DeviceRepository
import no.kristiania.dominigeiger.dto.DeviceMeasurementDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Timed
class DeviceController(
        @Autowired private val deviceRepository: DeviceRepository,
        @Autowired private val deviceMeasurementRepository: DeviceMeasurementRepository,
        @Autowired private val meterRegistry: MeterRegistry
) {

    @GetMapping("/devices")
    fun list(): ResponseEntity<List<Device>> {
        val devices = deviceRepository.findAll()

        return ResponseEntity.status(200).body(devices.toList())
    }

    @PostMapping("/devices")
    fun create(): ResponseEntity<Device> {
        val device = deviceRepository.save(Device())



        return ResponseEntity.ok(device)
    }

    @PostMapping("/devices/{deviceId}/measurements")
    fun createMeasurements(@PathVariable("deviceId") deviceId: UUID, @RequestBody rawMeasurement: DeviceMeasurementDto): ResponseEntity<Device> {
        val device = deviceRepository.findByIdOrNull(deviceId) ?: return ResponseEntity.status(404).body(null)
        deviceMeasurementRepository.save(DeviceMeasurement(
                rawMeasurement.longitude,
                rawMeasurement.latitude,
                rawMeasurement.sievert,
                device
        ))

        return ResponseEntity.ok(deviceRepository.findByIdOrNull(deviceId)!!)
    }

    @GetMapping("/devices/{deviceId}/measurements")
    fun getMeasurements(@PathVariable("deviceId") deviceId: UUID): ResponseEntity<List<DeviceMeasurement>> {
        val device = deviceRepository.findByIdOrNull(deviceId) ?: return ResponseEntity.status(404).body(null)
        return ResponseEntity.ok(device.measurements.toList())
    }

}