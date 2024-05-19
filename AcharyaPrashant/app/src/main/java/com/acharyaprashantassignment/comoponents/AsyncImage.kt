package com.acharyaprashantassignment.comoponents

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.acharyaprashantassignment.R
import com.acharyaprashantassignment.features.domain.models.ImageThumbnail
import com.acharyaprashantassignment.features.presentation.ThumbnailState
import com.acharyaprashantassignment.utilites.HomeScreenEvent
import com.acharyaprashantassignment.utilites.LocalSpacing


@Composable
fun AsyncImage(
    imageThumbnail: ImageThumbnail,
    thumbnailState: MutableState<ThumbnailState>,
    onEvent: (HomeScreenEvent) -> Unit
) {

    val context = LocalContext.current
    val localSpacing = LocalSpacing.current
    val placeholder = context.resources.getDrawable(R.drawable.placeholder, context.theme)

    Box(
        modifier = Modifier
            .widthIn(min = 80.dp)
            .heightIn(min = 80.dp)
    ) {
        if (thumbnailState.value.showLoader) {
            CircularProgressIndicator(
                strokeWidth = localSpacing.dp_2,
                color = Color.Blue,
                modifier = Modifier
                    .size(20.dp)
                    .widthIn(20.dp)
                    .heightIn(20.dp)
                    .align(Alignment.Center)
            )
        } else {
            Image(
                bitmap = thumbnailState.value.bitmap?.asImageBitmap()
                    ?: (placeholder as BitmapDrawable).bitmap.asImageBitmap(),
                contentDescription = "Thumbnail",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .widthIn(min = 80.dp)
                    .heightIn(min = 80.dp)
            )
        }

        if (thumbnailState.value.showRetry) {
            Text(text = stringResource(id = R.string.retry))
        }
    }

    LaunchedEffect(key1 = true) {
        onEvent(HomeScreenEvent.LoadImage(imageThumbnail, thumbnailState))
    }


}