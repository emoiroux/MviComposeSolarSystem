package mvi.compose.planets.presentation.planet

import mvi.compose.planets.model.PlanetModel
import mvi.compose.planets.presentation.MviState

sealed class PlanetScreenState(description: String) : MviState(description) {
    object Loading : PlanetScreenState("Loading data state")
    data class Success(val planets: List<PlanetModel>) :
        PlanetScreenState("Data loaded state")
    object Empty : PlanetScreenState("No data state")
    data class Error(val error: String?) : PlanetScreenState("Failed to retrieve data state")
}