package cargo

import HandlingEvent
import location.Location
import java.time.ZonedDateTime
import java.util.*

data class Cargo(val id: UUID, var goal: DeliverySpecification, val deliveryHistory: DeliveryHistory) {
    var destination: Location
        get() = this.goal.destination
        set(value) {
            this.goal = DeliverySpecification(ZonedDateTime.now(), value)
        }
}

interface CargoFactory {
    fun createMeBro(goal: DeliverySpecification, deliveryHistory: Collection<HandlingEvent>): Cargo
}

data class DeliveryHistory(val events: List<HandlingEvent>)

data class DeliverySpecification(val arrivalTime: ZonedDateTime, val destination: Location)

class Role