package block.interview.takehome.etiennemoiroux.presentation.employees

import block.interview.takehome.etiennemoiroux.presentation.MviIntent

sealed class EmployeesScreenIntent : MviIntent {
    object Load : EmployeesScreenIntent()
}