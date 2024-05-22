package pl.meksu.rickandmortyapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.meksu.rickandmortyapp.domain.CharacterRepository
import pl.meksu.rickandmortyapp.domain.model.CharacterResponse

class MainViewModel: ViewModel() {
    private val _repository = CharacterRepository()
    private val _characterState = mutableStateOf(CharacterState())
    val characterState: State<CharacterState> = _characterState
    private val _pagesInfo = mutableIntStateOf(0)
    val maxPage: State<Int> = _pagesInfo
    private val _currentPage = mutableIntStateOf(1)
    val pageState: State<Int> = _currentPage

    init {
        fetchCharacters(1)
    }

    private fun fetchCharacters(page: Int) {
        viewModelScope.launch {
            try {
                val response: CharacterResponse = _repository.getCharacters(page)
                _characterState.value = _characterState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )
                _pagesInfo.intValue = response.info.pages
            } catch(e: Exception) {
                _characterState.value = _characterState.value.copy(
                    loading = false,
                    error = "Error while fetching characters: ${e.message}"
                )
            }
        }
    }

    fun loadNextPage() {
        if(_currentPage.intValue < _pagesInfo.intValue) {
            _currentPage.intValue++
            fetchCharacters(_currentPage.intValue)
        }
    }

    fun loadPreviousPage() {
        if (_currentPage.intValue > 1) {
            _currentPage.intValue--
            fetchCharacters(_currentPage.intValue)
        }
    }

    fun refreshCharacters() {
        fetchCharacters(1)
    }
}