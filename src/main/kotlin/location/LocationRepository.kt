package location

interface LocationRepository {
    fun findByPortCode(portCode: String): Location
    fun findByCityName(cityName: String): Location
}