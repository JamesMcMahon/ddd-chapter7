package handlingEvent

import cargo.Cargo
import carrierMovement.CarrierMovement
import java.time.ZonedDateTime

data class HandlingEvent(
    val handled: Cargo,
    val type: HandlingEventType,
    val completionTime: ZonedDateTime,
    private var movement: CarrierMovement? = null
) {
    companion object {
        fun newLoading(c: Cargo, loadedOnto: CarrierMovement, timestamp: ZonedDateTime): HandlingEvent {
            val result = HandlingEvent(c, HandlingEventType.LOADING, timestamp)
            result.movement = loadedOnto
            return result
        }
    }
}

enum class HandlingEventType {
    LOADING,
    UNLOADING
}