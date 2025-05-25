package com.ganeshrashinkar.shopflowapplication.util

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ganeshrashinkar.shopflowapplication.R

class FontFamily {
    companion object {
        val tangerineFontFamily = FontFamily(
            Font(
                R.font.tangerine,
                FontWeight.Normal
            ) // Assuming your font file is named "my_custom_font.ttf" in res/font
        )

        // If you have different font weights (e.g., bold, italic), you can define them like this:
        val tangerineFontFamilyBold = FontFamily(
            Font(R.font.tangerine, FontWeight.Normal),
            Font(R.font.tangerine, FontWeight.Bold),
            // Add more weights and styles as needed
        )
    }
}