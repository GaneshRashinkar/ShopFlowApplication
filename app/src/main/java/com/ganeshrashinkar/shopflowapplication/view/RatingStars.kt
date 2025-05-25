package com.ganeshrashinkar.shopflowapplication.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun RatingStars(
    rating: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val isStarred = i <= rating
                Icon(
                    imageVector = if (isStarred) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = if (isStarred) "Rated $i stars" else "Rate $i stars",
                    tint = if (isStarred) Color.Yellow else Color.Gray
                )
        }
    }
}