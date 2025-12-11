package com.recurt.feature.creature.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.recurt.feature.creature.domain.model.CreatureListItem
import com.recurt.feature.creature.domain.usecase.GetCreatureListUseCase
import kotlinx.coroutines.flow.Flow

class CreatureListViewModel(
    private val getCreatureListUseCase: GetCreatureListUseCase
) : ViewModel() {
    val creaturePagingFlow: Flow<PagingData<CreatureListItem>> =
        getCreatureListUseCase()
            .cachedIn(viewModelScope)

    fun handleIntent(intent: CreatureListIntent) {
        when (intent) {
            is CreatureListIntent.SelectCreature -> { /* Navigation handled in UI */ }
        }
    }
}