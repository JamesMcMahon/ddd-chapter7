package customer

interface CustomerRepository {
    fun findByCustomerId(customerId: String): Customer
    fun findByName(name: String): Customer
    fun findByCargoTrackingId(trackingId: String): Customer
}