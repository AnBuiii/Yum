package com.anbui.yum.screens.feed

//import com.example.yum.datastore.DataStoreRepository
import androidx.compose.runtime.mutableStateOf
import com.anbui.yum.screens.YumViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(

) : YumViewModel() {

    val uiState = mutableStateOf(FeedUiState())

    private val tabState
        get() = uiState.value.tabState

    fun scrollToPage(a: Int) {
//        uiState.value.tabState.
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