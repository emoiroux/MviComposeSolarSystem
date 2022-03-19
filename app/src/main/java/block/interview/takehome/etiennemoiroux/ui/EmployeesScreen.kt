package block.interview.takehome.etiennemoiroux.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import block.interview.takehome.etiennemoiroux.R
import block.interview.takehome.etiennemoiroux.data.mock.MockEmployeeDataSet
import block.interview.takehome.etiennemoiroux.model.EmployeeModel
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesScreenState
import block.interview.takehome.etiennemoiroux.ui.theme.InterviewEienneMoirouxTheme
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

const val DEBUG_EMPLOYEE_SCREEN: Boolean = false

@Composable
fun EmployeesScreen(state: LiveData<EmployeesScreenState>, reload: () -> Unit) {
    val screenStateValue = state.observeAsState().value

    InterviewEienneMoirouxTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (screenStateValue) {
                is EmployeesScreenState.Loading -> EmployeesLoadingScreen()
                is EmployeesScreenState.Error -> EmployeesErrorScreen(
                    reload,
                    screenStateValue.error
                )
                is EmployeesScreenState.Success ->
                    EmployeesListScreen(reload, screenStateValue.employees)
                is EmployeesScreenState.Empty -> EmployeesEmptyScreen()
                else -> null
            }
        }
    }
}

@Composable
private fun EmployeesEmptyScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "No employee found.",
            modifier = Modifier.align(Alignment.Center) // or to a specific child
        )
    }
}

@Composable
private fun EmployeesListScreen(
    reload: () -> Unit,
    employees: List<EmployeeModel>
) {
    Column {
        ReloadButton(reload)
        EmployeeList(employees)
    }
}

@Composable
private fun EmployeesErrorScreen(
    reload: () -> Unit,
    error: String?
) {
    Column(modifier = Modifier.padding(8.dp)) {
        ReloadButton(reload)
        Text("Error : $error")
    }
}

@Composable
fun EmployeesLoadingScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text("Loading")
        Spacer(modifier = Modifier.height(5.dp))
        LinearProgressIndicator()
    }
}

@Composable
fun ReloadButton(reload: () -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        Button(
            onClick = { reload.invoke() }, colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.LightGray
            ), modifier = Modifier.align(Alignment.TopCenter)

        ) {
            Text("Refresh")
        }
    }
}

@Composable
fun EmployeeList(employees: List<EmployeeModel>) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(employees.size) { employeeIndex ->
            EmployeeCard(employees[employeeIndex])
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun EmployeeCard(it: EmployeeModel) {
    Card(
        backgroundColor = Color.LightGray.copy(alpha = 0.8f), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {
            Text("${it.full_name}  |  ${it.team}", Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.height(5.dp))
            it.photo_url_small?.let { smallImage -> EmployeeImage(smallImage) }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Email : ${it.email_address}")
            it.phone_number?.let { phoneNumber -> Text("Phone : $phoneNumber") }
            Text("Employee_type :  ${it.employee_type}")
            Spacer(modifier = Modifier.height(5.dp))
            it.biography?.let { biography ->
                Text(biography)
            }

            if (DEBUG_EMPLOYEE_SCREEN) {
                Text("Uuid : ${it.uuid}")
                it.photo_url_large?.let { largeImage -> EmployeeImage(largeImage) }
            }
        }
    }
}

@Composable
fun EmployeeImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .diskCacheKey(url)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        error = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "employee image",
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}

@Preview
@Composable
fun MoviesListPreview() {
    val employees = MockEmployeeDataSet.employees
    InterviewEienneMoirouxTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            EmployeesListScreen(
                {},
                employees
            )
        }
    }
}