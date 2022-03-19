package block.interview.takehome.etiennemoiroux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesScreenIntent
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesViewModel
import block.interview.takehome.etiennemoiroux.ui.EmployeesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: EmployeesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeesScreen(state = viewModel.state, reload = ::reloadClick)
        }
        sendIntent(EmployeesScreenIntent.Load)
    }

    private fun sendIntent(intent: EmployeesScreenIntent) {
        viewModel.processIntents(intent)
    }

    private fun reloadClick() {
        sendIntent(EmployeesScreenIntent.Load)
    }
}