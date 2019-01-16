import java.time.ZonedDateTime
import java.util.*

data class Customer(val id: String, val name: String, val role: String)

interface CargoFactory {
    fun createMeBro(goal: DeliverySpecification, deliveryHistory: Collection<HandlingEvent>): Cargo
}

data class Cargo(val id: UUID, val goal: DeliverySpecification, val deliveryHistory: DeliveryHistory)

data class DeliveryHistory(val events: List<HandlingEvent>)

data class DeliverySpecification(val arrivalTime: ZonedDateTime, val destination: Location)

data class Location(val id: UUID, val portCode: String)

class ShippingSchedule

interface CarrierMovementFactory {
    fun create(shippingSchedule: ShippingSchedule): CarrierMovement
}

data class CarrierMovement(val scheduleId: String, val from: Location, val to: Location)

data class HandlingEvent(val completionTime: ZonedDateTime, val type: HandlingEventType, val handled: Cargo, val movement: CarrierMovement?)

class Role

enum class HandlingEventType {
    LOADING,
    UNLOADING
}