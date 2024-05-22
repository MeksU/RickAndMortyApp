package pl.meksu.rickandmortyapp.data

import pl.meksu.rickandmortyapp.domain.model.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharacterResponse
}

private val retrofit = Retrofit.Builder()
    .baseUrl("https://rickandmortyapi.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val rickAndMortyService: ApiService? = retrofit.create(ApiService::class.java)