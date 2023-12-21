package com.shankarlohar.teamvinayak.ui.newuserside.component.newuser

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.GymInfo
import com.shankarlohar.teamvinayak.ui.common.AutoSlidingCarousel
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.ChooseUserViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun JoinNowCard(
    viewModel: ChooseUserViewModel,
    navController: NavController,
    gymInfo: GymInfo,
    context: Context,
) {

    val images = listOf(
        "https://firebasestorage.googleapis.com/v0/b/team-vinayak.appspot.com/o/vinayak_main_1.jpg?alt=media&token=e5af93d1-0cc4-4618-be2d-9418ec4b80c1",
        "https://firebasestorage.googleapis.com/v0/b/team-vinayak.appspot.com/o/vinayak_main_2.jpg?alt=media&token=77f32971-cf8e-4f8e-a9ff-de40950607d3"
    )
    val compositionRegister by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.register))


    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder // Image to display in case of an error
            (LocalContext.current).data(data = gymInfo.logo)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
                placeholder(R.drawable.vinayak_multi_gym_no_background) // Placeholder image while loading
                error(R.drawable.maintanance) // Image to display in case of an error
                scale(Scale.FILL) // Scale type
            }).build()
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.gym_name),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.padding(1.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card(
                modifier = Modifier.padding(2.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                AutoSlidingCarousel(
                    itemsCount = images.size,
                    itemContent = { index ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(images[index])
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(300.dp)
                        )
                    }
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center
            ){
                LottieAnimation(
                    modifier = Modifier.fillMaxWidth(),
                    composition = compositionRegister,
                    iterations = LottieConstants.IterateForever,
                )
                ElevatedAssistChip(
                    onClick = { navController.navigate(Steps.ONBOARD.name) },
                    label = { Text("Register Now!") },
                )
            }
        }
        Spacer(Modifier.padding(4.dp))
    }

}