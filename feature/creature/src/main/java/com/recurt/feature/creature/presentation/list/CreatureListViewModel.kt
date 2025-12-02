package com.recurt.feature.creature.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recurt.core.common.util.Constants
import com.recurt.core.common.util.Resource
import com.recurt.feature.creature.domain.usecase.GetCreatureListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreatureListViewModel(
    private val getCreatureListUseCase: GetCreatureListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CreatureListState())
    val state: StateFlow<CreatureListState> = _state.asStateFlow()

    init {
        handleIntent(CreatureListIntent.LoadCreatures)
    }

    fun handleIntent(intent: CreatureListIntent) {
        when (intent) {
            is CreatureListIntent.LoadCreatures -> loadCreatures()
            is CreatureListIntent.LoadMore -> loadMore()
            is CreatureListIntent.Refresh -> refresh()
            is CreatureListIntent.SelectCreature -> { /* Navigation handled in UI */ }
        }
    }

    private fun loadCreatures() {
        viewModelScope.launch {
            getCreatureListUseCase(page = 0, pageSize = Constants.PAGE_SIZE)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.update { it.copy(isLoading = true, error = null) }
                        }
                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    creatureListItem = result.data ?: emptyList(),
                                    isLoading = false,
                                    error = null,
                                    currentPage = 0,
                                    canLoadMore = (result.data?.size ?: 0) == Constants.PAGE_SIZE
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

    private fun loadMore() {
        if (_state.value.isLoadingMore || !_state.value.canLoadMore) return

        viewModelScope.launch {
            val nextPage = _state.value.currentPage + 1
            getCreatureListUseCase(page = nextPage, pageSize = Constants.PAGE_SIZE)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.update { it.copy(isLoadingMore = true) }
                        }
                        is Resource.Success -> {
                            val newCreatures = result.data ?: emptyList()
                            _state.update {
                                it.copy(
                                    creatureListItem = it.creatureListItem + newCreatures,
                                    isLoadingMore = false,
                                    currentPage = nextPage,
                                    canLoadMore = newCreatures.size == Constants.PAGE_SIZE
                                )
                            }
                        }
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isLoadingMore = false,
                                    error = result.message
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun refresh() {
        loadCreatures()
    }
}