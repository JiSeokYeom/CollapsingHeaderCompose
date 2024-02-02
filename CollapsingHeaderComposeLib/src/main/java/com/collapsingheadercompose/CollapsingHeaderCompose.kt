package com.collapsingheadercompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollapsingHeaderCompose(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    isScrollEnabled: Boolean = true,
    isTopContentVisible: Boolean = true,
    isStickyHeaderVisible: Boolean = true,
    onChangeHeader:(Boolean) -> Unit,
    stickyHeaderContent: @Composable LazyItemScope.() -> Unit = {},
    topContent: @Composable ColumnScope.() -> Unit = {},
    lazyColumnContent: LazyListScope.() -> Unit,
){

    LaunchedEffect(listState) {
        var oldOffset = listState.firstVisibleItemScrollOffset
        snapshotFlow { listState.firstVisibleItemScrollOffset }.collect { offset ->
            val isScrollingUp = oldOffset > offset
            oldOffset = offset

            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset

            when {
                // If you start scrolling down from the top
                firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset > 0 && !isScrollingUp -> {
                    onChangeHeader(false)
                }
                // When you start scrolling up from the first item
                firstVisibleItemIndex == 0 && isScrollingUp -> {
                    onChangeHeader(true)
                }
            }
        }
    }

    Column(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = isTopContentVisible,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            topContent()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            userScrollEnabled = isScrollEnabled
        ) {
            if (isStickyHeaderVisible){
                stickyHeader {
                    stickyHeaderContent()
                }
            }

            lazyColumnContent()


        }
    }

}