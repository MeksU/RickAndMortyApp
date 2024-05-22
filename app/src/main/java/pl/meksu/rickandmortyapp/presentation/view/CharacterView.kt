package pl.meksu.rickandmortyapp.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.meksu.rickandmortyapp.R
import pl.meksu.rickandmortyapp.presentation.viewmodel.MainViewModel
import pl.meksu.rickandmortyapp.domain.model.Character
import pl.meksu.rickandmortyapp.presentation.view.components.CharactersList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterView(
    navigateToDetail: (Character) -> Unit,
    characterViewModel: MainViewModel
) {
    val viewState by characterViewModel.characterState
    val currentPage by characterViewModel.pageState
    val allPages by characterViewModel.maxPage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                title = {
                    Text(
                        text = "Rick And Morty",
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .heightIn(max = 24.dp)
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when {
                viewState.loading -> {
                    CircularProgressIndicator()
                }
                viewState.error != null -> {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Something went wrong!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Check your internet connection",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = viewState.error!!,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        IconButton(onClick = { characterViewModel.refreshCharacters() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh characters button"
                            )
                        }
                    }
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 64.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CharactersList(
                                characters = viewState.list,
                                navigateToDetail = navigateToDetail
                            )
                        }
                        HorizontalDivider(Modifier.height(8.dp))
                        Row(
                            Modifier
                                .height(64.dp)
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .background(colorResource(id = R.color.bottom_row))
                                .border(
                                    BorderStroke(0.dp, Color.Gray),
                                    shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { characterViewModel.loadPreviousPage() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Previous page button",
                                    tint = if(currentPage != 1) Color.Black else Color.Gray
                                )
                            }
                            Text("Page ${currentPage}/${allPages}")
                            IconButton(onClick = { characterViewModel.loadNextPage() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Next page button",
                                    tint = if(currentPage != allPages) Color.Black else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}