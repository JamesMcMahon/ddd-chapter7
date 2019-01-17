package carrierMovement

import location.Location

interface CarrierMovementRepository {
    fun findByScheduleId(scheduleId: String): CarrierMovement
    fun findByFromTo(from: Location, to: Location): CarrierMovement
}