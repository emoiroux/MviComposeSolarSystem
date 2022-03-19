package block.interview.takehome.etiennemoiroux

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import block.interview.takehome.etiennemoiroux.data.mock.MockEmployeeDataSet
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesScreenState
import block.interview.takehome.etiennemoiroux.ui.EmployeesScreen
import block.interview.takehome.etiennemoiroux.ui.theme.InterviewEienneMoirouxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmployeesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loadingState() {
        composeTestRule.setContent {
            InterviewEienneMoirouxTheme {
                EmployeesScreen(state = MutableLiveData(EmployeesScreenState.Loading)) {}
            }
        }
        composeTestRule.onNodeWithText("Loading").assertIsDisplayed()
    }

    @Test
    fun successState() {
        composeTestRule.setContent {
            InterviewEienneMoirouxTheme {
                EmployeesScreen(
                    state = MutableLiveData(
                        EmployeesScreenState.Success(
                            MockEmployeeDataSet.employees
                        )
                    )
                ) {}
            }
        }

        composeTestRule.onNodeWithText("Refresh").assertExists()
        composeTestRule.onNodeWithText(
            "jmason.mock@squareup.com",
            substring = true,
            ignoreCase = true
        ).assertExists()
        composeTestRule.onNodeWithText("etienne.mock@me.com", substring = true, ignoreCase = true)
            .assertExists()
    }
}