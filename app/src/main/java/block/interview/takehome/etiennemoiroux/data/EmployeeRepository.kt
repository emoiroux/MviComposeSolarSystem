package block.interview.takehome.etiennemoiroux.data

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

// https://s3.amazonaws.com/sq-mobile-interview/employees.json
@Singleton
class EmployeeRepository @Inject constructor
    (
    private val employeeService: IEmployeesService,
) : IEmployeesRepository {

    override suspend fun getEmployees(): Response<EmployeesResponse> {
        return employeeService.getEmployees()
    }
}