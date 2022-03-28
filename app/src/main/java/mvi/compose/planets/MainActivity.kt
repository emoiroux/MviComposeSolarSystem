package mvi.compose.planets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mvi.compose.planets.presentation.planet.PlanetScreenIntent
import mvi.compose.planets.presentation.planet.PlanetViewModel
import mvi.compose.planets.ui.PlanetScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: PlanetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetScreen(state = viewModel.state, reload = ::reloadClick)
        }

        if (savedInstanceState == null) {
            sendIntent(PlanetScreenIntent.Load)
        }
    }

    private fun sendIntent(intent: PlanetScreenIntent) {
        viewModel.processIntents(intent)
    }

    private fun reloadClick() {
        sendIntent(PlanetScreenIntent.Load)
    }
}