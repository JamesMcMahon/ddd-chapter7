package cargo

import carrierMovement.CarrierMovement
import handlingEvent.HandlingEvent
import handlingEvent.HandlingEventType
import location.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import java.util.*

internal class CargoTest {
    @Test
    fun `changing the destination of a cargo`() {
        val cargo = Cargo(
            UUID.randomUUID(),
            DeliverySpecification(ZonedDateTime.now(), Location(UUID.randomUUID(), "abc")),
            DeliveryHistory()
        )

        val newDestination = Location(UUID.randomUUID(), "zzz")

        cargo.destination = newDestination

        assertThat(cargo.goal.destination).isEqualTo(newDestination)
    }

    @Test
    fun `repeat business`() {
        val cargo = Cargo(
            UUID.randomUUID(),
            DeliverySpecification(ZonedDateTime.now(), Location(UUID.randomUUID(), "abc")),
            DeliveryHistory()
        )

        cargo.deliveryHistory.addEvent(
            HandlingEvent(
                cargo,
                HandlingEventType.LOADING,
                ZonedDateTime.now(),
                CarrierMovement("123", Location(UUID.randomUUID(), "Boston"), Location(UUID.randomUUID(), "London"))
            )
        )

        val newCargo = cargo.copyPrototype(UUID.randomUUID())

        assertThat(newCargo.id).isNotEqualTo(cargo.id)
        assertThat(newCargo.deliveryHistory.events).isEmpty()
    }

    @Test
    fun `adding a handling event`() {
        val cargo = Cargo(
            UUID.randomUUID(),
            DeliverySpecification(ZonedDateTime.now(), Location(UUID.randomUUID(), "abc")),
            DeliveryHistory()
        )

        val carrierMovement = CarrierMovement(
            "id",
            Location(UUID.randomUUID(), "Boston"),
            Location(UUID.randomUUID(), "London")
        )

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
}