package block.interview.takehome.etiennemoiroux.presentation.employees

import android.util.Log
import androidx.lifecycle.viewModelScope
import block.interview.takehome.etiennemoiroux.data.IEmployeesRepository
import block.interview.takehome.etiennemoiroux.di.DefaultDispatcher
import block.interview.takehome.etiennemoiroux.model.EmployeeModel
import block.interview.takehome.etiennemoiroux.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val repository: IEmployeesRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) :
    BaseViewModel<EmployeesScreenState, EmployeesScreenIntent>(defaultDispatcher) {

    override fun processIntents(intent: EmployeesScreenIntent) {
        when (intent) {
            EmployeesScreenIntent.Load -> fetchEmployees()
        }
    }

    override fun getInitialState(): EmployeesScreenState {
        return EmployeesScreenState.Loading
    }

    private fun fetchEmployees() {
        viewModelScope.launch(defaultDispatcher) {
            try {
                updateState {
                    EmployeesScreenState.Loading
                }
                repository.getEmployees().body()?.employees.let { rawEmployeesList ->
                    val employeesList = transform(rawEmployeesList)

                    if (employeesList.isEmpty()) {
                        updateState {
                            EmployeesScreenState.Empty
                        }
                    } else {
                        updateState {
                            EmployeesScreenState.Success(employeesList)
                        }
                    }
                }
            } catch (e: Exception) {
                updateState {
                    EmployeesScreenState.Error(e.message)
                }
            }
        }

    }

    private fun transform(rawList: List<EmployeeModel>?): List<EmployeeModel> {
        // filter invalid
        val filteredList = rawList?.filter { it.valid() }
        val employeeRemoved = (rawList?.size ?: 0) - (filteredList?.size ?: 0)
        if (employeeRemoved > 0) {
            Log.i(
                EmployeesViewModel::class.simpleName,
                "$employeeRemoved employee(s) are malformed, they have been removed from the list."
            )
        }
        // sort and group
        val sortedGroupedMap =
            filteredList?.sortedBy { it.full_name }?.groupBy { it.employee_type }?.values
        val sortedGroupedList = mutableListOf<EmployeeModel>()
        sortedGroupedMap?.forEach { list -> sortedGroupedList.addAll(list) }

        return sortedGroupedList
    }
}