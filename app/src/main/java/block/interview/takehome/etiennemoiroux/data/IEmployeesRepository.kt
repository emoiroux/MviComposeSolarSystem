package block.interview.takehome.etiennemoiroux.data

import retrofit2.Response

interface IEmployeesRepository {
     suspend fun getEmployees () : Response<EmployeesResponse>
}
