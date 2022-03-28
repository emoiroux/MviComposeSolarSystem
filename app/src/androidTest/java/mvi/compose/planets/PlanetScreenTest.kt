package mvi.compose.planets

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import mvi.compose.planets.data.mock.MockPlanetDataSet
import mvi.compose.planets.presentation.planet.PlanetScreenState
import mvi.compose.planets.ui.PlanetScreen
import mvi.compose.planets.ui.theme.PlanetsTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlanetScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loadingState() {
        composeTestRule.setContent {
            PlanetsTheme {
                PlanetScreen(state = MutableLiveData(PlanetScreenState.Loading)) {}
            }
        }
        composeTestRule.onNodeWithText("Loading").assertIsDisplayed()
    }

    @Test
    fun successState() {
        composeTestRule.setContent {
            PlanetsTheme {
                PlanetScreen(
                    state = MutableLiveData(
                        PlanetScreenState.Success(
                            MockPlanetDataSet.planets
                        )
                    )
                ) {}
            }
        }

        composeTestRule.onNodeWithText("Refresh").assertExists()
        composeTestRule.onNodeWithText("Mercury is the closest planet", substring = true, ignoreCase = true)
            .assertExists()
    }
}