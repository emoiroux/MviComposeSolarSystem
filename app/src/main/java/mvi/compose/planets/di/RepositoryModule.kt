package mvi.compose.planets.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import mvi.compose.planets.data.IPlanetRepository
import mvi.compose.planets.data.IPlanetService
import mvi.compose.planets.data.PlanetRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providePlanetRepository(
        moviesApi: IPlanetService,
    ): IPlanetRepository {
        return PlanetRepository(moviesApi)
    }
}