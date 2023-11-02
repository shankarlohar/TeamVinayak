package com.shankarlohar.teamvinayak.ui.authentication.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.data.model.OnBoardingModel
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoarding(
    onBoardingContent: List<OnBoardingModel>,
    onJoinClick: () -> Unit = {},
    onBackToLoginClick: () -> Unit = {},
    context: Context,
) {
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopSection(
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.scrollToPage(pageState.currentPage - 1)
                }
                else{
                    onBackToLoginClick()
                }
            },
            onSkipClick = {
                if (pageState.currentPage + 1 < onBoardingContent.size) scope.launch {
                    pageState.scrollToPage(onBoardingContent.size - 1)
                }
                if (pageState.currentPage != onBoardingContent.size - 1) {
                    Toast.makeText(context, R.string.wohh_that_was_fast, Toast.LENGTH_SHORT).show()
                }
            }
        )

        HorizontalPager(
            count = onBoardingContent.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()
        ) { page ->
            OnBoardingItem(
                items = onBoardingContent[page]
            )
        }

        BottomSection(
            size = onBoardingContent.size,
            index = pageState.currentPage,
        ) {
            if (pageState.currentPage + 1 < onBoardingContent.size) scope.launch {
                pageState.scrollToPage(pageState.currentPage + 1)
            }else{
                onJoinClick()
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TopSection(
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null
            )
        }

        // Skip Button
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier
                .align(
                    Alignment.CenterEnd
                ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = stringResource(R.string.skip),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun BottomSection(
    size: Int,
    index: Int,
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        Indicators(size, index)

        // FAB Next
        FloatingActionButton(
            onClick =  onButtonClick,
            containerColor = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
        ) {
            if (index == size - 1){
                Text(
                    text = stringResource(R.string.fill_form),
                    modifier = Modifier
                        .padding(8.dp)
                        .size(200.dp),
                    textAlign = TextAlign.Center
                )
            }else{
                Icon(Icons.Outlined.KeyboardArrowRight,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.next)
                )
            }
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(
                isSelected = it == index
            )
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
            )
    ) {

    }
}

@Composable
fun OnBoardingItem(items: OnBoardingModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = items.image),
            contentDescription = "Image1",
            modifier = Modifier.padding(start = 50.dp, end = 50.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(id = items.title),
            style = MaterialTheme.typography.headlineMedium,
            // fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = items.desc),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp),
            letterSpacing = 1.sp,
        )
    }
}