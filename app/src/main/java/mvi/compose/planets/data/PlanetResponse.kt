package mvi.compose.planets.data

import mvi.compose.planets.model.PlanetModel

data class PlanetResponse(
    var planets: List<PlanetModel>
)