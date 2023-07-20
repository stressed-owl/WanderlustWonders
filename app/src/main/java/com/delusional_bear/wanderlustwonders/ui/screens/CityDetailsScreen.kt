package com.delusional_bear.wanderlustwonders.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.delusional_bear.wanderlustwonders.R
import com.delusional_bear.wanderlustwonders.data.DataSource

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

    val city = dataSource.getCityById(cityId)
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.Hidden,
            skipPartiallyExpanded = false,
        )
    )

    BottomSheetScaffold(
        sheetContent = {  },
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
            val matrix = ColorMatrix()
            matrix.setToSaturation(0.6F)
            AsyncImage(
                model = city?.imageURL,
                contentDescription = city?.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animateImageSize)
                    .clip(RoundedCornerShape(bottomStart = 48.dp, bottomEnd = 48.dp)),
                colorFilter = ColorFilter.colorMatrix(matrix),
            )
            Spacer(modifier = Modifier.height(4.dp))
            RichTooltipBox(
                text = {
                    Text(
                        text = stringResource(id = R.string.hide_image_desc),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.hide_image),
                        style = MaterialTheme.typography.titleSmall,
                    )
                },
                action = {
                    TextButton(onClick = { }) {
                        Text(
                            text = stringResource(id = R.string.learn_more),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                },
                modifier = Modifier
            ) {
                IconButton(
                    onClick = {
                        imageState = if (imageState == ImageState.Normal) {
                            ImageState.Hidden
                        } else {
                            ImageState.Normal
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.TopEnd)
                        .tooltipAnchor(),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.hide_image),
                        contentDescription = null,
                    )
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

