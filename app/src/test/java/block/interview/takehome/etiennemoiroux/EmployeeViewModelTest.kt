package block.interview.takehome.etiennemoiroux

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import block.interview.takehome.etiennemoiroux.data.EmployeesResponse
import block.interview.takehome.etiennemoiroux.data.IEmployeesRepository
import block.interview.takehome.etiennemoiroux.data.mock.MockEmployeeDataSet
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesViewModel
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesScreenIntent
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesScreenState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
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
class EmployeeStateTest {
    private val mockRepository: IEmployeesRepository = mock()
    private lateinit var viewModel: EmployeesViewModel

    @ExperimentalCoroutinesApi
    var coroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(coroutineDispatcher)

        viewModel = EmployeesViewModel(
            mockRepository,
            coroutineDispatcher
        )
    }

    @Test
    fun `test initial state`() {
        viewModel.state.observeForTesting { observer ->
            assertThat(
                viewModel.state.value,
                iz(EmployeesScreenState.Loading)
            ) // initial should be loading
            assertThat(observer.values.size, iz(1)) // only 1 postValue(..)
        }
    }

    @Test
    fun `test success state`() {
        coroutineDispatcher.runBlockingTest {
            whenever(mockRepository.getEmployees())
                .thenReturn(Response.success(EmployeesResponse(MockEmployeeDataSet.employees)))
        }

        viewModel.state.observeForTesting { observer ->
            assertThat(viewModel.state.value is EmployeesScreenState.Loading, iz(true))
            viewModel.processIntents(EmployeesScreenIntent.Load)
            assertThat(viewModel.state.value is EmployeesScreenState.Success, iz(true))
            assertThat(
                (viewModel.state.value as EmployeesScreenState.Success).employees.size,
                iz(MockEmployeeDataSet.employees.size)
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
            whenever(mockRepository.getEmployees())
                .thenReturn(Response.success(EmployeesResponse(MockEmployeeDataSet.malformedEmployees)))
        }

        viewModel.state.observeForTesting { observer ->
            assertThat(viewModel.state.value is EmployeesScreenState.Loading, iz(true))
            viewModel.processIntents(EmployeesScreenIntent.Load)
            assertThat(viewModel.state.value is EmployeesScreenState.Success, iz(true))
            assertThat(
                (viewModel.state.value as EmployeesScreenState.Success).employees.size,
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
            whenever(mockRepository.getEmployees())
                .thenReturn(Response.success(EmployeesResponse(MockEmployeeDataSet.emptyEmployees)))
        }

        viewModel.state.observeForTesting {
            assertThat(viewModel.state.value is EmployeesScreenState.Loading, iz(true))
            viewModel.processIntents(EmployeesScreenIntent.Load)
            assertThat(viewModel.state.value is EmployeesScreenState.Empty, iz(true))
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