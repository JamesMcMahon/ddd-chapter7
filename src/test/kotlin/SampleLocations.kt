import shipping.location.Location
import java.util.*

class SampleLocations {
    companion object {
        val BOSTON = Location(UUID.randomUUID(), "Boston")
        val LONDON = Location(UUID.randomUUID(), "London")
        val CHICAGO = Location(UUID.randomUUID(), "Chicago")
        val DUBLIN = Location(UUID.randomUUID(), "Dublin")
    }
}
