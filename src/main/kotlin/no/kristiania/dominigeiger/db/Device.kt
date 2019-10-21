package no.kristiania.dominigeiger.db

import com.fasterxml.jackson.annotation.JsonManagedReference
import java.util.*
import javax.persistence.*

@Entity()
class Device(
        @get:JsonManagedReference
        @get:OneToMany(mappedBy = "device", fetch = FetchType.LAZY)
        var measurements: Set<DeviceMeasurement> = setOf(),

        @get:Id
        @get:GeneratedValue
        var id: UUID? = null
)