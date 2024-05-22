package pl.meksu.rickandmortyapp.domain

import pl.meksu.rickandmortyapp.data.rickAndMortyService
import pl.meksu.rickandmortyapp.domain.model.CharacterResponse

class CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterResponse {
        return rickAndMortyService!!.getCharacters(page)
    }
}