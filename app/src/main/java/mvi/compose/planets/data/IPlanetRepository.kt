package mvi.compose.planets.data

import retrofit2.Response

interface IPlanetRepository {
     suspend fun getPlanet () : Response<PlanetResponse>
}