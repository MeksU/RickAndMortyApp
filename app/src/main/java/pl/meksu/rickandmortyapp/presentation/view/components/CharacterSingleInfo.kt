package pl.meksu.rickandmortyapp.presentation.view.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CharacterSingleInfo(desc: String, value: String) {
    Text(
        text = desc,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    Text(
        text = value,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp
    )
    Spacer(modifier = Modifier.height(12.dp))
}