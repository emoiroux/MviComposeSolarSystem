package block.interview.takehome.etiennemoiroux.di

import block.interview.takehome.etiennemoiroux.data.EmployeeRepository
import block.interview.takehome.etiennemoiroux.data.IEmployeesService
import block.interview.takehome.etiennemoiroux.data.IEmployeesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideEmployeeRepository(
        moviesApi: IEmployeesService,
    ): IEmployeesRepository {
        return EmployeeRepository(moviesApi)
    }
}