package shipping.carrierMovement

import shipping.location.Location

data class CarrierMovement(val scheduleId: String, val from: Location, val to: Location)

interface CarrierMovementFactory {
    fun create(shippingSchedule: ShippingSchedule): CarrierMovement
}

class ShippingSchedule
