package shipping.cargo

import shipping.handlingEvent.HandlingEvent
import shipping.location.Location
import java.time.ZonedDateTime
import java.util.*

data class Cargo(val id: UUID, var goal: DeliverySpecification, val deliveryHistory: DeliveryHistory) {
    var destination: Location
        get() = this.goal.destination
        set(value) {
            this.goal = DeliverySpecification(ZonedDateTime.now(), value)
        }

    fun copyPrototype(id: UUID): Cargo {
        return this.copy(id = id, deliveryHistory = DeliveryHistory())
    }

    fun addEvent(event: HandlingEvent) {
        this.deliveryHistory.add(event)
    }
}

interface CargoFactory {
    fun createMeBro(goal: DeliverySpecification, deliveryHistory: Collection<HandlingEvent>): Cargo
}

data class DeliveryHistory(private val _events: MutableList<HandlingEvent> = mutableListOf()) {
    val events: List<HandlingEvent>
        get() = this._events

    internal fun add(event: HandlingEvent) {
        this._events.add(event)
    }
}

data class DeliverySpecification(val arrivalTime: ZonedDateTime, val destination: Location)

class Role