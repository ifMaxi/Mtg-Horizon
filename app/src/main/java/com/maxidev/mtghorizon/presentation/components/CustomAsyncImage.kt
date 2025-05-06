package com.maxidev.mtghorizon.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import com.maxidev.mtghorizon.presentation.theme.MTGHorizonTheme

@Composable
fun CustomAsyncImage(
    imageUrl: String,
    name: String,
    // Custom options
    contentScale: ContentScale = ContentScale.Fit,
    height: Dp = Dp.Unspecified,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    if (imageUrl.isNotEmpty()) {
        // TODO: Add shimmer effect or placeholder.
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = contentScale,
            modifier = modifier
                .height(height)
                .clickable { onClick() }
        )
    }
}

@Preview
@Composable
fun CardItemPreview() {
    MTGHorizonTheme {
        CustomAsyncImage(
            imageUrl = "image",
            name = "Some card"
        )
    }
}