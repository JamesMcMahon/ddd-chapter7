import SampleLocations.Companion.BOSTON
import SampleLocations.Companion.CHICAGO
import SampleLocations.Companion.DUBLIN
import SampleLocations.Companion.LONDON
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import shipping.cargo.Cargo
import shipping.cargo.DeliveryHistory
import shipping.cargo.DeliverySpecification
import shipping.carrierMovement.CarrierMovement
import shipping.handlingEvent.HandlingEvent
import shipping.handlingEvent.HandlingEventType
import java.time.ZonedDateTime
import java.util.*

internal class ApplicationTest {
    @Test
    internal fun `changing the destination of a cargo`() {
        val cargo = createCargo()
        val newDestination = DUBLIN

        cargo.destination = newDestination

        assertThat(cargo.goal.destination).isEqualTo(newDestination)
    }

    @Test
    internal fun `repeat business`() {
        val cargo = createCargo()

        cargo.addEvent(
            HandlingEvent(
                cargo,
                HandlingEventType.LOADING,
                ZonedDateTime.now(),
                CarrierMovement("123", BOSTON, LONDON)
            )
        )

        val newCargo = cargo.copyPrototype(UUID.randomUUID())

        assertThat(newCargo.id).isNotEqualTo(cargo.id)
        assertThat(newCargo.deliveryHistory.events).isEmpty()
    }

    @Test
    internal fun `adding a handling event`() {
        val cargo = createCargo()

        val carrierMovement = CarrierMovement("id", BOSTON, LONDON)

        val completionTime = ZonedDateTime.now()
        val expectedHandling = HandlingEvent(
            cargo,
            HandlingEventType.LOADING,
            completionTime,
            carrierMovement
        )

        val handlingEvent = HandlingEvent.newLoading(cargo, carrierMovement, completionTime)

        assertThat(handlingEvent).isEqualTo(expectedHandling)
    }

    private fun createCargo() = Cargo(
        UUID.randomUUID(),
        DeliverySpecification(ZonedDateTime.now(), CHICAGO),
        DeliveryHistory()
    )
}
