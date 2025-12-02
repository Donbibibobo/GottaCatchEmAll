package com.recurt.feature.creature.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.usecase.GetCreatureDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatureDetailViewModel(
    private val creatureId: String,
    private val getCreatureDetailUseCase: GetCreatureDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreatureDetailState())
    val state: StateFlow<CreatureDetailState> = _state.asStateFlow()

    init {
        handleIntent(CreatureDetailIntent.LoadDetail(creatureId))
    }

    fun handleIntent(intent: CreatureDetailIntent) {
        when (intent) {
            is CreatureDetailIntent.LoadDetail -> loadDetail(intent.id)
            is CreatureDetailIntent.NavigateBack -> { /* Handled in UI */ }
        }
    }

    private fun loadDetail(id: String) {
        viewModelScope.launch {
            getCreatureDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true, error = null) }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                creature = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }
}