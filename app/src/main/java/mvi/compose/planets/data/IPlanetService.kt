package mvi.compose.planets.data

import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface IPlanetService {
    @GET("planets.json")
    suspend fun getPlanets(): Response<PlanetResponse>
}