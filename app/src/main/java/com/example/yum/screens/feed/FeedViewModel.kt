package com.example.yum.screens.feed

//import com.example.yum.datastore.DataStoreRepository
import androidx.compose.runtime.mutableStateOf
import com.example.yum.model.service.LogService
import com.example.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    logService: LogService,
) : YumViewModel(logService) {

    val uiState = mutableStateOf(FeedUiState())

    private val tabState
        get() = uiState.value.tabState

    fun scrollToPage(a: Int) {

    }

    private val searchText
        get() = uiState.value.searchText

    fun scrollToTab(index: Int) {
        uiState.value = uiState.value.copy(tabState = index)
    }

    fun onSearchTextChange(newValue: String) {
        uiState.value = uiState.value.copy(searchText = newValue)
    }

}