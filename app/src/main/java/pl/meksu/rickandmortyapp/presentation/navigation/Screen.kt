package pl.meksu.rickandmortyapp.presentation.navigation

sealed class Screen(val route: String) {
    data object CharactersScreen: Screen("character_screen")
    data object DetailScreen: Screen("detail_screen")
}