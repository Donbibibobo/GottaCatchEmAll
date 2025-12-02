package com.recurt.feature.creature.di

import com.recurt.feature.creature.config.DigimonConfig
import com.recurt.feature.creature.data.DigimonRepositoryImpl
import com.recurt.feature.creature.data.remote.api.DigimonApi
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
        DigimonConfig()
    }

    single<DigimonApi> {
        get<Retrofit.Builder>()
            .baseUrl("https://digi-api.com/api/v1/")
            .build()
            .create(DigimonApi::class.java)
    }

    single<CreatureRepository> {
        DigimonRepositoryImpl(digimonApi = get())
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