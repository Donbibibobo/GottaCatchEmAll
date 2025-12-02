package com.recurt.feature.creature.di

import com.recurt.feature.creature.config.PokemonConfig
import com.recurt.feature.creature.data.PokemonRepositoryImpl
import com.recurt.feature.creature.data.remote.api.PokemonApi
import com.recurt.feature.creature.domain.config.CreatureConfig
import com.recurt.feature.creature.domain.repository.CreatureRepository
import com.recurt.feature.creature.domain.usecase.GetCreatureDetailUseCase
import com.recurt.feature.creature.domain.usecase.GetCreatureListUseCase
import com.recurt.feature.creature.presentation.detail.CreatureDetailViewModel
import com.recurt.feature.creature.presentation.list.CreatureListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val creatureModule = module {

    single<CreatureConfig> {
        PokemonConfig()
    }

    single<PokemonApi> {
        get<Retrofit.Builder>()
            .baseUrl("https://pokeapi.co/api/v2/")
            .build()
            .create(PokemonApi::class.java)
    }

    single<CreatureRepository> {
        PokemonRepositoryImpl(pokemonApi = get())
    }

    single { GetCreatureListUseCase(repository = get()) }
    single { GetCreatureDetailUseCase(repository = get()) }

    viewModel {
        CreatureListViewModel(getCreatureListUseCase = get())
    }

    viewModel { (id: String) ->
        CreatureDetailViewModel(
            creatureId = id,
            getCreatureDetailUseCase = get()
        )
    }
}