package shipping.cargo

interface CargoRepository {
    fun findByCargoCustomerId(customerId: String): Cargo
    fun findByCargoTrackingId(trackingId: String): Cargo
}