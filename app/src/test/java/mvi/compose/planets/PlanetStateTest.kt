package mvi.compose.planets

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import mvi.compose.planets.data.IPlanetRepository
import mvi.compose.planets.data.PlanetResponse
import mvi.compose.planets.data.mock.MockPlanetDataSet
import mvi.compose.planets.presentation.planet.PlanetScreenIntent
import mvi.compose.planets.presentation.planet.PlanetScreenState
import mvi.compose.planets.presentation.planet.PlanetViewModel
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response
import org.hamcrest.core.Is.`is` as iz

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class PlanetStateTest {
    private val mockRepository: IPlanetRepository = mock()
    private lateinit var viewModel: PlanetViewModel

    @ExperimentalCoroutinesApi
    var coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)

        viewModel = PlanetViewModel(
            mockRepository,
            coroutineDispatcher
        )
    }

    @Test
    fun `test initial state`() {
        viewModel.state.observeForTesting { observer ->
            assertThat(
                viewModel.state.value,
                iz(PlanetScreenState.Loading)
            ) // initial should be loading
            assertThat(observer.values.size, iz(1)) // only 1 postValue(..)
        }
    }

    @Test
    fun `test success state`() {
        coroutineDispatcher.runBlockingTest {
            whenever(mockRepository.getPlanet())
                .thenReturn(Response.success(PlanetResponse(MockPlanetDataSet.planets)))
        }

        viewModel.state.observeForTesting { observer ->
            assertThat(viewModel.state.value is PlanetScreenState.Loading, iz(true))
            viewModel.processIntents(PlanetScreenIntent.Load)
            assertThat(viewModel.state.value is PlanetScreenState.Success, iz(true))
            assertThat(
                (viewModel.state.value as PlanetScreenState.Success).planets.size,
                iz(MockPlanetDataSet.planets.size)
            )
            assertThat(
                observer.values.size,
                iz(3)
            ) //  3 => 1 initial state , 2 loading before fetch, 3 success.
            coroutineDispatcher.resumeDispatcher()
        }
    }

    @Test
    fun `test success state with 2 malformed employees`() {
        coroutineDispatcher.runBlockingTest {
            whenever(mockRepository.getPlanet())
                .thenReturn(Response.success(PlanetResponse(MockPlanetDataSet.planets)))
        }

        viewModel.state.observeForTesting { observer ->
            assertThat(viewModel.state.value is PlanetScreenState.Loading, iz(true))
            viewModel.processIntents(PlanetScreenIntent.Load)
            assertThat(viewModel.state.value is PlanetScreenState.Success, iz(true))
            assertThat(
                (viewModel.state.value as PlanetScreenState.Success).planets.size,
                iz(1)
            )
            assertThat(
                observer.values.size,
                iz(3)
            ) //  3 => 1 initial state , 2 loading before fetch, 3 success.
            coroutineDispatcher.resumeDispatcher()
        }
    }

    @Test
    fun `test empty state`() {
        coroutineDispatcher.runBlockingTest {
            whenever(mockRepository.getPlanet())
                .thenReturn(Response.success(PlanetResponse(MockPlanetDataSet.planets)))
        }

        viewModel.state.observeForTesting {
            assertThat(viewModel.state.value is PlanetScreenState.Loading, iz(true))
            viewModel.processIntents(PlanetScreenIntent.Load)
            assertThat(viewModel.state.value is PlanetScreenState.Empty, iz(true))
            coroutineDispatcher.resumeDispatcher()
        }
    }

    fun tearDown() {
        Dispatchers.resetMain()
        coroutineDispatcher.cleanupTestCoroutines()
    }
}

fun <T> LiveData<T>.observeForTesting(block: (ValuesObserver<T>) -> Unit) {
    val observer = ValuesObserver<T>()
    try {
        observeForever(observer)
        block(observer)
    } finally {
        removeObserver(observer)
    }
}

class ValuesObserver<T> : Observer<T> {
    val values = mutableListOf<T>()

    override fun onChanged(t: T) {
        values.add(t)
    }
}