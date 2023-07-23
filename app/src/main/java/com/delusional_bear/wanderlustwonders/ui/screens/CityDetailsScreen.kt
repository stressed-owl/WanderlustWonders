package com.delusional_bear.wanderlustwonders.ui.screens

import android.content.Intent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.delusional_bear.wanderlustwonders.R
import com.delusional_bear.wanderlustwonders.data.DataSource
import com.delusional_bear.wanderlustwonders.ui.elements.buttons.CityDetailsIconButton

enum class ImageState(val height: Dp) {
    Normal(330.dp),
    Hidden(0.dp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailsScreen(modifier: Modifier = Modifier, dataSource: DataSource, cityId: Int) {
    var imageState by remember { mutableStateOf(ImageState.Normal) }
    val transition = updateTransition(targetState = imageState, label = null)
    val animateImageSize by transition.animateDp(label = "") { it.height }
    val context = LocalContext.current

    val city = dataSource.getCityById(cityId)

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.Hidden,
            skipPartiallyExpanded = false,
        )
    )

    BottomSheetScaffold(
        sheetContent = { },
        sheetPeekHeight = 210.dp,
        scaffoldState = bottomSheetState,
        modifier = modifier,
        sheetShadowElevation = 4.dp,
        sheetShape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
        sheetTonalElevation = 4.dp,
        sheetDragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(),
        ) {
            AsyncImage(
                model = city?.imageURL,
                contentDescription = city?.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animateImageSize)
                    .clip(RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                CityDetailsIconButton(
                    tooltipText = R.string.hide_image_desc,
                    tooltipTitle = R.string.hide_image,
                    toolTipButtonText = R.string.learn_more,
                    buttonIcon = R.drawable.hide_image,
                ) {
                    imageState = if (imageState == ImageState.Normal)
                        ImageState.Hidden
                    else
                        ImageState.Normal
                }
                Spacer(modifier = Modifier.width(8.dp))
                CityDetailsIconButton(
                    tooltipText = R.string.share_desc,
                    tooltipTitle = R.string.share,
                    toolTipButtonText = R.string.learn_more,
                    buttonIcon = R.drawable.share_icon,
                ) {
                    val shareMessage = context.resources.getString(
                        R.string.share_message,
                        city?.name,
                        city?.country,
                        city?.state ?: "",
                        city?.description?.let { desc -> context.resources.getString(desc) }
                    )
                    val intent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, shareMessage)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent, null)
                    context.startActivity(shareIntent)
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = city?.description ?: R.string.no_city_found),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Justify,
                    lineHeight = 20.sp,
                )
            }
        }
    }
}

