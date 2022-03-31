package com.brodgate.marvelapi

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

fun LazyListState.isScrolledToEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
