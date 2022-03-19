package block.interview.takehome.etiennemoiroux.presentation.employees

import block.interview.takehome.etiennemoiroux.model.EmployeeModel
import block.interview.takehome.etiennemoiroux.presentation.MviState

sealed class EmployeesScreenState(description: String) : MviState(description) {
    object Loading : EmployeesScreenState("Loading data state")
    data class Success(val employees: List<EmployeeModel>) :
        EmployeesScreenState("Data loaded state")
    object Empty : EmployeesScreenState("No data state")
    data class Error(val error: String?) : EmployeesScreenState("Failed to retrieve data state")
}