package mvi.compose.planets.data

import mvi.compose.planets.data.PlanetResponse
import retrofit2.Response

interface IPlanetRepository {
     suspend fun getPlanet () : Response<PlanetResponse>
}