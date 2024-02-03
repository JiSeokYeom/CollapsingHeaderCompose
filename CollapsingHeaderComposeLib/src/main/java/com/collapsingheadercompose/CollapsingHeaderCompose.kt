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

/**
 * @param modifier 기본 modifier를 설정 해준다. (Sets the basic modifier.)
 * @param listState 해당 함수 호출전 val lazyListState = rememberLazyListState() 통해 LazyListState 전달
 * (Before calling the function, pass LazyListState through val lazyListState = rememberLazyListState())
 * @param isTopContentVisible TopContent가 보여질지 숨길지 정하는 변수 보통은 var isTopContentVisible by remember { mutableStateOf(true) }
 * 형태로 넘겨 받음
 * (A variable that determines whether TopContent is visible or hidden. Normally, var isTopContentVisible by remember { mutableStateOf(true) }
 * Handed over in the form)
 * @param isStickyHeaderVisible StickyHeader가 보여질지 숨길지 정하는 변수 (Variable that determines whether StickyHeader will be shown or hidden)
 * @param onChangeTopContent 스크롤에 따라 TopContent가 보여질지 숨길지 정하는 콜백 함수 (A callback function that determines whether TopContent is shown or hidden according to scrolling.)
 * @param topContent 가장 상단에 보여줄 컴포저블 함수 (Composable function shown at the top)
 * @param stickyHeaderContent stickyHeader 영역인 컴포저블 함수 (Composable function with stickyHeader area)
 * @param lazyColumnContent 리스트를 보여줄 아이템 item, items 선택 가능 (Items to display the list can be selected.)
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CollapsingHeaderCompose(
    modifier: Modifier = Modifier,
    listState: LazyListState,
    isTopContentVisible: Boolean,
    isScrollEnabled: Boolean = true,
    isStickyHeaderVisible: Boolean = true,
    onChangeTopContent:(Boolean) -> Unit,
    topContent: @Composable ColumnScope.() -> Unit = {},
    stickyHeaderContent: @Composable LazyItemScope.() -> Unit = {},
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
                    onChangeTopContent(false)
                }
                // When you start scrolling up from the first item
                firstVisibleItemIndex == 0 && isScrollingUp -> {
                    onChangeTopContent(true)
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