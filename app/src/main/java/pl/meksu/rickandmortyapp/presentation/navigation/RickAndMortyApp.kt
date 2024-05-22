package pl.meksu.rickandmortyapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pl.meksu.rickandmortyapp.presentation.viewmodel.MainViewModel
import pl.meksu.rickandmortyapp.domain.model.Character
import pl.meksu.rickandmortyapp.domain.model.Location
import pl.meksu.rickandmortyapp.domain.model.Origin
import pl.meksu.rickandmortyapp.presentation.view.CharacterDetailView
import pl.meksu.rickandmortyapp.presentation.view.CharacterView

@Composable
fun RickAndMortyApp(navController: NavHostController) {
    val characterViewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Screen.CharactersScreen.route) {
        composable(route = Screen.CharactersScreen.route) {
            CharacterView(
                navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("character", it)
                    navController.navigate(Screen.DetailScreen.route)
                },
                characterViewModel = characterViewModel
            )
        }
        composable(route = Screen.DetailScreen.route) {
            val character =
                navController.previousBackStackEntry?.savedStateHandle?.get<Character>("character")
                    ?: Character(0, "", "", "", "", "", Origin("", ""),
                        Location("", ""), "", emptyList(), "", "")

            CharacterDetailView(
                character = character,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}