package com.collapsingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.collapsingcompose.ui.theme.CollapsingHeaderComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        /*    CollapsingHeaderComposeTheme {
                val lazyListState = rememberLazyListState()
                var isTopContentVisible by remember { mutableStateOf(true) }
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CollapsingHeaderCompose(
                        modifier = Modifier.fillMaxSize(),
                        listState = lazyListState,
                        isTopContentVisible = isTopContentVisible,
                        onChangeTopContent = { isChange ->
                            isTopContentVisible = isChange
                        },
                        topContent = {
                            Column {
                                repeat(2) { index ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFFECF8E0))
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 30.dp),
                                            textAlign = TextAlign.Center,
                                            text = "I'm TopContent $index !!",
                                            fontSize = 17.sp,
                                        )
                                    }
                                }
                            }
                        },
                        stickyHeaderContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFFA8258))
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 18.dp),
                                    textAlign = TextAlign.Center,
                                    text = "I'm Sticky Header!!",
                                    fontSize = 15.sp,
                                )
                            }
                        },
                        lazyColumnContent = {
                            items(30) { indext ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .border(BorderStroke(1.dp, Color.Black))
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 15.dp),
                                        textAlign = TextAlign.Center,
                                        text = "$indext",
                                        fontSize = 14.sp,
                                    )
                                }
                            }
                        }
                    )
                }
            }*/
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CollapsingHeaderComposeTheme {
        Greeting("Android")
    }
}