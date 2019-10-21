package no.kristiania.dominigeiger.db

import com.fasterxml.jackson.annotation.JsonBackReference
import java.util.*
import javax.persistence.*

@Entity
class DeviceMeasurement(
        var longitude: Float? = null,
        var latitude: Float? = null,
        var sievert: Int? = null,

        @get:JsonBackReference
        @get:ManyToOne(fetch = FetchType.LAZY)
        @get:JoinColumn(name="device_id", nullable=false)
        var device: Device? = null,

        @get:Id
        @get:GeneratedValue
        var id: UUID? = null
)