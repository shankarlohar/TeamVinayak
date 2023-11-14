package com.shankarlohar.teamvinayak.ui.newuserside

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shankarlohar.teamvinayak.R
import com.shankarlohar.teamvinayak.model.SignupFormModel
import com.shankarlohar.teamvinayak.util.Steps
import com.shankarlohar.teamvinayak.viewmodel.AuthViewModel
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun NewUserFormComponent(
    viewModel: AuthViewModel,
    navController: NavHostController,
) {
    val items by viewModel.signupFormData.collectAsState()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState()
    val context = LocalContext.current

    val sectionChange = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FormTopSection(
            item = items[pageState.currentPage]
        )

        HorizontalPager(
            count = items.size,
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(),
            userScrollEnabled = false
        ) { page ->
            FormItem(
                section = items[page].field,
                questions = items[page].data,
                viewModel = viewModel,
                sectionChange = sectionChange,
                context = context
            ){
                if (sectionChange.value){
                    sectionChange.value = false
                    if (pageState.currentPage < items.size - 1) scope.launch {
                        pageState.scrollToPage(pageState.currentPage + 1)
                        if (pageState.currentPage == items.size - 1){
                            Toast.makeText(context,"All done!",Toast.LENGTH_LONG).show()
                            viewModel.createNewMember()
                        }
                    }
                }
            }
        }

        FormBottomSection(
            size = items.size,
            sectionChange = sectionChange,
            index = pageState.currentPage,
        ) {
              navController.navigate(Steps.CHOICE.name)

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormItem(
    section: String,
    questions: List<String>,
    viewModel: AuthViewModel,
    sectionChange: MutableState<Boolean>,
    context: Context,
    changePage: () -> Unit,
) {
        var currentIndex by remember { mutableStateOf(0) }
        val updatedCurrentIndex = rememberUpdatedState(currentIndex)
        val ans = remember{ mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            if (updatedCurrentIndex.value <= questions.size - 1) {
                Text(
                        text = questions[updatedCurrentIndex.value],
                        style = TextStyle(fontSize = 32.sp),
                    )
                if (section.substring(3) == "Declaration" && updatedCurrentIndex.value == 0) {
                    Button(
                        onClick = {
                            ans.value = ""
                            currentIndex++
                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(text = "Yes I confirm")
                    }
                }else if(section.substring(3) == "All Done"){
                    // animation here
                }

                else{
                    OutlinedTextField(
                        value = ans.value,
                        onValueChange = {
                            ans.value = it
                        }
                    )
                    Button(
                        onClick = {

                            val validity = viewModel.checkAnswer(ans.value,questions[updatedCurrentIndex.value])
                            if (validity.first){
                                viewModel.addQuestion(
                                    section,
                                    questions[updatedCurrentIndex.value],
                                    ans.value
                                )
                                ans.value = ""
                                currentIndex++
                            }else{
                                Toast.makeText(context,validity.second,Toast.LENGTH_SHORT).show()
                            }

                        },
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            } else {
                sectionChange.value = true
                changePage()
            }


        }
}

@ExperimentalPagerApi
@Composable
fun FormTopSection(
    item: SignupFormModel,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        Text(
            text = item.field.substring(3),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = if (item.field.length < 30) 16.sp else 12.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun FormBottomSection(
    size: Int,
    sectionChange: MutableState<Boolean>,
    index: Int,
    onButtonClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        FormIndicators(size, index)

        // FAB Next
        if(sectionChange.value || (index == size - 1)) {
            FloatingActionButton(
                onClick = onButtonClick,
                containerColor = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
            ) {
                Text(
                    text = stringResource(R.string.go_back_to_choice),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .heightIn(min = 48.dp)
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun BoxScope.FormIndicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            FormIndicator(
                isSelected = it == index
            )
        }
    }
}

@Composable
fun FormIndicator(isSelected: Boolean) {
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


