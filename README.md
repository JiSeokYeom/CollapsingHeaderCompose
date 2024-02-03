# CollapsingHeaderCompose (Anto)

## Introduction

[![](https://jitpack.io/v/JiSeokYeom/CollapsingHeaderCompose.svg)](https://jitpack.io/#JiSeokYeom/CollapsingHeaderCompose)

<h3> This library provides the Collapsing function for Header Content.

  Additionally, a collapsing function is provided automatically depending on the scrolling response.
  
<h3> 이 라이브러리는 Header Content를 Collapsing 기능을 제공하는 라이브러리 입니다.

  또한 스크롤반응에 따라 자동으로 Collapsing 기능을 제공 합니다.
</h3>

## Preview

<img src="https://github.com/JiSeokYeom/CollapsingHeaderCompose/assets/38849158/00de2e0f-015f-4d72-95f5-e0be7b9e8527" width="30%">

## How to add to your project

build.gradle project 수준에 아래 maven url를 적어준다.

Add dependencies for Jitpack in build.gradle(project)

```kotlin
  	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

build.gradle module 수준에 아래 의존성을 추가해준다 이거는 Groovy 버전

Add dependencies for libraries in build.gradle(module) - Groovy
```kotlin
  	dependencies {
                implementation 'com.github.JiSeokYeom:CollapsingHeaderCompose:$LATEST_VERSION'
	}
```

build.gradle module 수준에 아래 의존성을 추가해준다 이거는 Kotlin DSL 버전

Add dependencies for libraries in build.gradle(module) - Kotlin DSL
```kotlin
  	dependencies {
               implementation("com.github.JiSeokYeom:CollapsingHeaderCompose:$LATEST_VERSION")
	}
```

## Example code

```kotlin

CollapsingHeaderComposeTheme {
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
                             // TODO topContent
                        },
                        stickyHeaderContent = {
                            // TODO stickyHeaderContent
                        },
                        lazyColumnContent = {
                            // TODO lazyColumnContent (item, items)
                        }
                    )
                }

```

## Property

```kotlin

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
)

```

기본 modifier를 설정 해준다. (옵션 / 기본값 - Modifier)
Sets the basic modifier (option / Default - Modifier)

```kotlin
 modifier: Modifier = Modifier
```

해당 함수 호출전 LazyListState 전달. (필수)
Pass LazyListState before calling the function (required)

```kotlin
 listState: LazyListState
```

TopContent가 보여질지 숨길지 정하는 변수 (필수)
Variable that determines whether TopContent is shown or hidden (required)

```kotlin
 isTopContentVisible: Boolean
```

스크롤 비활성화 / 활성화 시키는 변수 (옵션 / 기본값 - true)
Variable for disabling/activating scrolling (option / Default - true)

```kotlin
 isScrollEnabled: Boolean = true
```

StickyHeader가 보여질지 숨길지 정하는 변수 (옵션 / 기본 값 - true)
Variable that determines whether StickyHeader will be shown or hidden (option / Default - true)

```kotlin
 isStickyHeaderVisible: Boolean = true
```

스크롤에 따라 TopContent가 보여질지 숨길지 정하는 콜백 함수 (필수 / 리턴값 Boolean)
A callback function that determines whether TopContent is shown or hidden according to scrolling (required / Return value Boolean)

```kotlin
 onChangeTopContent:(Boolean) -> Unit
```

가장 상단에 보여줄 컴포저블 함수 (옵션 / 기본 값 - Unit)
Composable function shown at the top (option / Default - Unit)

```kotlin
 topContent: @Composable ColumnScope.() -> Unit = {}
```

stickyHeader 영역인 컴포저블 함수 (옵션 / 기본 값 - Unit)
Composable function with stickyHeader area (option / Default - Unit)

```kotlin
 stickyHeaderContent: @Composable LazyItemScope.() -> Unit = {}
```

리스트를 보여줄 아이템 item, items 선택 가능 (필수)
Items to display the list can be selected (required)

```kotlin
 lazyColumnContent: LazyListScope.() -> Unit
```

## Etc

오류나 문의사항이 있으시면 이슈탭에 남겨주세요 감사합니다! :)
If you have any errors or questions, please leave them on the issue tab Thank you! :)
