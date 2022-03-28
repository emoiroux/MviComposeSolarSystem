package mvi.compose.planets.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.Password
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import mvi.compose.planets.model.PlanetModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import mvi.compose.planets.R
import mvi.compose.planets.data.mock.MockPlanetDataSet
import mvi.compose.planets.presentation.planet.PlanetScreenState
import mvi.compose.planets.ui.theme.PlanetsTheme

@Composable
fun PlanetScreen(state: LiveData<PlanetScreenState>, reload: () -> Unit) {
    val screenStateValue = state.observeAsState().value

    PlanetsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            when (screenStateValue) {
                is PlanetScreenState.Loading -> EmployeesLoadingScreen()
                is PlanetScreenState.Error -> EmployeesErrorScreen(
                    reload,
                    screenStateValue.error
                )
                is PlanetScreenState.Success ->
                    EmployeesListScreen(reload, screenStateValue.planets)
                is PlanetScreenState.Empty -> EmployeesEmptyScreen()
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
    planets: List<PlanetModel>
) {
    Column {
        ReloadButton(reload)
        EmployeeList(planets)
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
            Text("Refresh", Modifier.testTag("refreshid").layoutId("refreshLayoutId").semantics {
                heading()
                text = AnnotatedString("refrech id tecxt")
                contentDescription = "text content description"
            })
        }
    }
}

@Composable
fun EmployeeList(planets: List<PlanetModel>) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(planets.size) { employeeIndex ->
            EmployeeCard(planets[employeeIndex])
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
fun EmployeeCard(it: PlanetModel) {
    Card(
        backgroundColor = Color.LightGray.copy(alpha = 0.8f), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
        ) {

            Text("${it.name} | ${it.distance} MKm", Modifier.align(Alignment.CenterHorizontally)
                    .testTag("refresh id").layoutId("refreshLayoutId").semantics {
                    text = AnnotatedString("refresh id text")
                    contentDescription = "text content description"

                })
            Spacer(modifier = Modifier.height(5.dp))
            it.image?.let { image -> EmployeeImage(image) }
            Spacer(modifier = Modifier.height(5.dp))
            Text("Email : ${it.distance}")
            Spacer(modifier = Modifier.height(5.dp))
            it.description?.let { description ->
                Text(description)
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
            .height(300.dp)
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 20.dp),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
    )
}

@Preview
@Composable
fun MoviesListPreview() {
    val employees = MockPlanetDataSet.planets
    PlanetsTheme {
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