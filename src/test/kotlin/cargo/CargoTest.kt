package cargo

import location.Location
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime
import java.util.*

internal class CargoTest {
    @Test
    internal fun `changing the destination of a cargo`() {
        val cargo = Cargo(
            UUID.randomUUID(),
            DeliverySpecification(ZonedDateTime.now(), Location(UUID.randomUUID(), "abc")),
            DeliveryHistory(emptyList())
        )

        val newDestination = Location(UUID.randomUUID(), "zzz")

        cargo.destination = newDestination

        assertThat(cargo.goal.destination).isEqualTo(newDestination)
    }
}