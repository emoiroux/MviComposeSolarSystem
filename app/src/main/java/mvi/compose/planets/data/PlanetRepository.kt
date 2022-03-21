package mvi.compose.planets.data

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetRepository @Inject constructor
    (
    private val PlanetService: IPlanetService,
) : IPlanetRepository {

    override suspend fun getPlanet(): Response<PlanetResponse> {
        return PlanetService.getPlanets()
    }
}