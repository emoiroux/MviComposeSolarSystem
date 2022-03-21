package mvi.compose.planets.presentation.planet

import mvi.compose.planets.presentation.MviIntent


sealed class PlanetScreenIntent : MviIntent {
    object Load : PlanetScreenIntent()
}