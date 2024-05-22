package pl.meksu.rickandmortyapp.presentation.viewmodel

import pl.meksu.rickandmortyapp.domain.model.Character

data class CharacterState(
    val loading: Boolean = true,
    val list: List<Character> = emptyList(),
    val error: String? = null
)
