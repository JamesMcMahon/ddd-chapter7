import cargo.Cargo
import carrierMovement.CarrierMovement
import java.time.ZonedDateTime

data class HandlingEvent(
    val completionTime: ZonedDateTime,
    val type: HandlingEventType,
    val handled: Cargo,
    val movement: CarrierMovement?
)

enum class HandlingEventType {
    LOADING,
    UNLOADING
}