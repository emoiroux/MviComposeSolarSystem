package mvi.compose.planets.presentation.planet

import androidx.lifecycle.viewModelScope
import mvi.compose.planets.di.DefaultDispatcher
import mvi.compose.planets.model.PlanetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import mvi.compose.planets.data.IPlanetRepository
import mvi.compose.planets.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlanetViewModel @Inject constructor(
    private val repository: IPlanetRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) :
    BaseViewModel<PlanetScreenState, PlanetScreenIntent>(defaultDispatcher) {

    override fun processIntents(intent: PlanetScreenIntent) {
        when (intent) {
            PlanetScreenIntent.Load -> fetchEmployees()
        }
    }

    override fun getInitialState(): PlanetScreenState {
        return PlanetScreenState.Loading
    }

    private fun fetchEmployees() {
        viewModelScope.launch(defaultDispatcher) {
            try {
                updateState {
                    PlanetScreenState.Loading
                }
                repository.getPlanet().body()?.planets.let { rawEmployeesList ->
                    val employeesList = transform(rawEmployeesList)

                    if (employeesList.isEmpty()) {
                        updateState {
                            PlanetScreenState.Empty
                        }
                    } else {
                        updateState {
                            PlanetScreenState.Success(employeesList)
                        }
                    }
                }
            } catch (e: Exception) {
                updateState {
                    PlanetScreenState.Error(e.message)
                }
            }
        }
    }

    private fun transform(rawList: List<PlanetModel>?): List<PlanetModel> {
        // sort and group
        val sortedGroupedMap =
            rawList?.sortedBy { it.position }?.toList()

        return sortedGroupedMap ?: emptyList()
    }
}