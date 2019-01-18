package shipping.cargo

import shipping.handlingEvent.HandlingEvent
import shipping.location.Location
import java.time.ZonedDateTime
import java.util.*
import kotlin.collections.ArrayList

data class Cargo(val id: UUID, var goal: DeliverySpecification, val deliveryHistory: DeliveryHistory) {
    var destination: Location
        get() = this.goal.destination
        set(value) {
            this.goal = DeliverySpecification(ZonedDateTime.now(), value)
        }

    fun copyPrototype(id: UUID): Cargo {
        return this.copy(id = id, deliveryHistory = DeliveryHistory())
    }
}

interface CargoFactory {
    fun createMeBro(goal: DeliverySpecification, deliveryHistory: Collection<HandlingEvent>): Cargo
}

data class DeliveryHistory(private val initialEvents: List<HandlingEvent> = emptyList()) {
    val events = ArrayList<HandlingEvent>()

    init {
        this.events.addAll(initialEvents)
    }

    fun addEvent(event: HandlingEvent) {
        events.add(event)
    }
}

data class DeliverySpecification(val arrivalTime: ZonedDateTime, val destination: Location)

class Role