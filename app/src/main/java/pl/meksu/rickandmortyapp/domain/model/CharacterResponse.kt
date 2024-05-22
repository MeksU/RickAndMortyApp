package pl.meksu.rickandmortyapp.domain.model

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)