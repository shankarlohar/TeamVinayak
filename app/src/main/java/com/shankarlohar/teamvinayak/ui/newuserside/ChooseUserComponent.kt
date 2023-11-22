package com.shankarlohar.teamvinayak.ui.newuserside

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel
import com.shankarlohar.teamvinayak.data.choiceCategories
import com.shankarlohar.teamvinayak.data.choiceScreens
import com.shankarlohar.teamvinayak.model.ChooseUserModel
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.util.Utils
import com.shankarlohar.teamvinayak.ui.newuserside.component.AdminLoginCard
import com.shankarlohar.teamvinayak.ui.newuserside.component.JoinNowCard
import com.shankarlohar.teamvinayak.ui.newuserside.component.MemberLoginCard
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ChooseUserComponent(
    viewModel: ChooseUserViewModel,
    navController: NavController,
    authViewModel: AuthViewModel,
    gymInfo: GymInfo
) {
    val pagerState = rememberPagerState()
    val selectedCategory = remember { mutableStateOf(0) }
    val rememberScope = rememberCoroutineScope()



    LaunchedEffect(pagerState.currentPage){
        selectedCategory.value = pagerState.currentPage
    }


    Row(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            choiceCategories.forEachIndexed { index, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .height(90.dp)
                        .graphicsLayer {
                            rotationZ = -90f
                            translationX = 100f
                        }
                        .clickable {
                            selectedCategory.value = index
                            rememberScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (selectedCategory.value == index) FontWeight.Bold else FontWeight.Thin,
                    color = if (selectedCategory.value == index) Color.White else Color.LightGray,
                    maxLines = 1,
                )
            }
        }
        HorizontalPager(
            count = choiceScreens.size,
            contentPadding = PaddingValues(end = 70.dp, top = 70.dp),
            state = pagerState,
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
            ChoiceItem(
                item = choiceScreens[page],
                page = page,
                pageOffset = pageOffset,
                viewModel = viewModel,
                navController = navController,
                authViewModel = authViewModel,
                gymInfo = gymInfo
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoiceItem(
    item: ChooseUserModel,
    page: Int, pageOffset: Float,
    viewModel: ChooseUserViewModel,
    navController: NavController,
    authViewModel: AuthViewModel,
    gymInfo: GymInfo,

    ) {
    val scale = Utils.lerp(
        start = 0.5f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val angle = Utils.lerp(
        start = 30f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleXBox = Utils.lerp(
        start = 0.9f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val scaleYBox = Utils.lerp(
        start = 0.7f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val rotateY = Utils.lerp(
        start = 10f,
        stop = 0f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
    val boxAngle: Float by animateFloatAsState(
        targetValue = rotateY,

        animationSpec = tween(durationMillis = 600, easing = Utils.EaseOutQuart)
    )
    val boxScaleX: Float by animateFloatAsState(
        targetValue = scaleXBox,

        animationSpec = tween(durationMillis = 800, easing = Utils.EaseOutQuart)
    )
    val boxScaleY: Float by animateFloatAsState(
        targetValue = scaleYBox,

        animationSpec = tween(durationMillis = 800, easing = Utils.EaseOutQuart)
    )
    val imageAngle: Float by animateFloatAsState(
        targetValue = angle,

        animationSpec = tween(durationMillis = 600, easing = Utils.EaseOutQuart)
    )
    val visibility = Utils.lerp(
        start = 0f,
        stop = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )

    val context = LocalContext.current


    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            viewModel.screenState.value = ChooseUserViewModel.UiState.Details(item)
        }
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    Utils
                        .lerp(
                            start = 0.90f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        .also {
                            scaleX = boxScaleX
                            scaleY = boxScaleY
                            rotationY = boxAngle
                        }
                }
                .fillMaxHeight(.8f)
                .fillMaxWidth()
                .background(color = item.color.copy(alpha = .8f), RoundedCornerShape(20.dp))
                .padding(end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp)
                    .alpha(visibility)
            ) {
                Column {
                    Text(
                        text = stringResource(item.title),
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = stringResource(item.heading),
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(item.subHeading),
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = .9f),
                        fontWeight = FontWeight.Light
                    )
                    when(page){
                        0 -> JoinNowCard(navController = navController,gymInfo = gymInfo, viewModel = viewModel)
                        1 -> MemberLoginCard(navController = navController,authViewModel = authViewModel)
                        2 -> AdminLoginCard(navController = navController, authViewModel = authViewModel)
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {

            Image(
                painter = painterResource(id = item.bottomImage),
                contentDescription = "",
                modifier = Modifier
                    .align(
                        Alignment.BottomEnd
                    )
                    .rotate(330f)
                    .offset(x = 20.dp, y = 10.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        rotationZ = imageAngle
                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}
