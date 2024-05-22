package pl.meksu.rickandmortyapp.presentation.view.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.meksu.rickandmortyapp.domain.model.Character

@Composable
fun CharactersList(
    characters: List<Character>,
    navigateToDetail: (Character) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characters) {
            HorizontalDivider()
            CharacterItem(
                character = it,
                navigateToDetail = navigateToDetail
            )
        }
    }
}