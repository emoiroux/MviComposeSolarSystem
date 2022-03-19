package block.interview.takehome.etiennemoiroux.data

import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Singleton

//"employees_malformed.json"
//"employees_empty.json"
//"employees.json"
@Singleton
interface IEmployeesService {
    @GET("employees.json")
    suspend fun getEmployees(): Response<EmployeesResponse>
}