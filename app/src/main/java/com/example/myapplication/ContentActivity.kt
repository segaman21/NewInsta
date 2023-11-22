package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ContentScreen(navController: NavController) {
    var loading by remember { mutableStateOf(true) }
    var contentLoading by remember { mutableStateOf(true) }
    var openBottomSheet by remember { mutableStateOf(false) }
    var state by remember { mutableStateOf(periodList) }
    var images by remember { mutableStateOf(imagesForWeek) }

    LaunchedEffect(Unit) {
        val flag = navController.currentBackStackEntry?.savedStateHandle?.get<Boolean>(KEY)
        if (flag == true) {
            images = imagesForMonth
            state = periodList.map {
                if (it.id == 2) {
                    it.copy(isCheck = true)
                } else {
                    it.copy(isCheck = false)
                }
            }
        } else {
            images = imagesForWeek
            state = periodList
        }
    }

    LaunchedEffect(openBottomSheet) {
        images = if (state[0].isCheck) {
            imagesForWeek
        } else {
            imagesForMonth
        }
    }

    LaunchedEffect(state) {
        contentLoading = true
        delay(duration = Random.nextDouble(1.0, 1.8).seconds)
        contentLoading = false
    }

    PeriodBottomSheetUI(
        selectTrainingsSpentDialogShowed = openBottomSheet,
        onSelectGenderDialogDismiss = { openBottomSheet = false },
        onActionNavigate = { id ->
            openBottomSheet = false
            state = state.map { it.copy(isCheck = it.id == id) }
        },
        state = state
    )

    LaunchedEffect(Unit) {
        delay(Random.nextDouble(0.9, 1.8).seconds)
        loading = false
    }

    FitnessTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MyColors.Surface
        ) {
            Column {
                MainToolbar(
                    title = "Контент",
                    onBackArrowClick = { navController.popBackStack() }
                )
                if (loading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = FitnessTheme.colors.Gray3,
                            strokeWidth = 1.dp,
                        )
                    }
                } else {
                    Column {
                        ChipRow(
                            title = state.find { it.isCheck }?.text.orEmpty()
                        ) { openBottomSheet = true }
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFD3D3D3))
                                .height(1.dp)
                                .fillMaxWidth()
                        )
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Охват",
                            style = FitnessTheme.textStyles.title
                        )
                        if (contentLoading) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    color = FitnessTheme.colors.Gray3,
                                    strokeWidth = 1.dp,
                                )
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFD3D3D3))
                                    .height(1.dp)
                                    .fillMaxWidth()
                            )
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(3),
                                verticalArrangement = Arrangement.spacedBy(1.dp),
                                horizontalArrangement = Arrangement.spacedBy(1.dp)
                            ) {
                                item(span = { GridItemSpan(maxLineSpan) }) {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(all = 16.dp),
                                        text = "Ваши истории доступны для аудитории 24 часа. По истечении этого времени они появляются в этом разделе, и вы можете посмотреть их статистику. Статистику историй видите только вы.",
                                        color = Color(0xFF7E7E7E),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                images.forEach {
                                    item {
                                        Image(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(220.dp)
                                                .clickable(
                                                    indication = null,
                                                    interactionSource = remember { MutableInteractionSource() }
                                                ) {
                                                    if (it == R.drawable.content26) {
                                                        navController.navigate("stats")
                                                    } else {
                                                        navController.navigate("stats2")
                                                    }
                                                },
                                            contentScale = ContentScale.FillWidth,
                                            painter = painterResource(id = it),
                                            contentDescription = "",
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChipRow(
    title: String,
    openBottomSheet: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = spacedBy(8.dp)
    ) {
        Text(
            text = "Истории",
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .padding(vertical = 6.dp)
                .padding(horizontal = 12.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FitnessTheme.textStyles.body3.copy(
                fontWeight = FontWeight.W800,
                fontSize = 14.sp
            ),
        )
        Text(
            text = title,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .padding(vertical = 6.dp)
                .padding(horizontal = 12.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { openBottomSheet() },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = FitnessTheme.textStyles.body3.copy(
                fontWeight = FontWeight.W800,
                fontSize = 14.sp
            ),
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .padding(vertical = 6.dp)
                .padding(horizontal = 6.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.options),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PeriodBottomSheetUI(
    selectTrainingsSpentDialogShowed: Boolean,
    onActionNavigate: (Int) -> Unit,
    onSelectGenderDialogDismiss: () -> Unit,
    state: List<PeriodItem>
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val hideModalBottomSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
            onSelectGenderDialogDismiss()
        }
    }
    if (selectTrainingsSpentDialogShowed) {
        ModalBottomSheet(
            tonalElevation = 300.dp,
            dragHandle = {
                BottomSheetDefaults.DragHandle(
                    modifier = Modifier.offset(y = (-8).dp),
                    color = MyColors.Gray3
                )
            },
            shape = RoundedCornerShape(size = 14.dp),
            sheetState = sheetState,
            onDismissRequest = { hideModalBottomSheet() },
            contentColor = MyColors.Surface,
            containerColor = MyColors.Surface
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Выберите период времени",
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-8).dp),
                    textAlign = TextAlign.Center,
                    style = FitnessTheme.textStyles.title.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Separator()
                Spacer(modifier = Modifier.height(10.dp))
                state.forEach { item ->
                    Period(item = item, onSelect = { onActionNavigate(it) })
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}

@Composable
internal fun Period(
    item: PeriodItem,
    onSelect: (Int) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { onSelect(item.id) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.text,
            style = FitnessTheme.textStyles.title.copy(
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
        )
        RoundedCornerCheckBox(
            isChecked = item.isCheck,
        )
    }
}

@Composable
fun RoundedCornerCheckBox(
    isChecked: Boolean,
) {
    val checkedState = remember { mutableStateOf(isChecked) }
    Box(
        modifier = Modifier
            .padding(vertical = 14.dp)
            .padding(start = 12.dp)
            .size(24.dp)
            .border(
                width = 1.dp,
                color = if (checkedState.value) MyColors.LightBlue else MyColors.Gray2,
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(if (checkedState.value) MyColors.LightBlue else Color.White),
    ) {
        if (checkedState.value) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(8.dp)
                    .background(MyColors.Surface)
                    .align(Alignment.Center)
            )
        }
    }
}

data class PeriodItem(
    val id: Int,
    val text: String,
    val isCheck: Boolean
)

val periodList = listOf(
    PeriodItem(1, "Последние 7 дней", true),
    PeriodItem(2, "Последние 30 дней", false),
    PeriodItem(3, "Последние 3 месяца", false),
    PeriodItem(4, "Последние 6 месяцев", false),
    PeriodItem(5, "Последний год", false),
    PeriodItem(6, "Последние 2 года", false),
)

val imagesForWeek = listOf(
    R.drawable.content0,
    R.drawable.content1,
    R.drawable.content2,
    R.drawable.content3,
    R.drawable.content4,
    R.drawable.content5,
    R.drawable.content12,
    R.drawable.content13,
    R.drawable.content14,
    R.drawable.content15,
    R.drawable.content16,
    R.drawable.content17,
    R.drawable.content18,
    R.drawable.content19,
    R.drawable.content20,
    R.drawable.content21,
    R.drawable.content22,
    R.drawable.content23,
    R.drawable.content24,
    R.drawable.content25,
    R.drawable.content26,
    R.drawable.content27,
    R.drawable.content28,
    R.drawable.content29,
    R.drawable.content6,
    R.drawable.content7,
    R.drawable.content8,
    R.drawable.content9,
    R.drawable.content10,
    R.drawable.content11,
    R.drawable.content30,
    R.drawable.content31,
    R.drawable.content32,
    R.drawable.content33,
    R.drawable.content34,
    R.drawable.content35,
)

val imagesForMonth = listOf(
    R.drawable.stor_30,
    R.drawable.stor_31,
    R.drawable.stor_32,
    R.drawable.stor_33,
    R.drawable.con_34,
    R.drawable.con_35,
    R.drawable.con_36,
    R.drawable.con_37,
    R.drawable.con_38,
    R.drawable.con_39,
    R.drawable.con_40,
    R.drawable.con_41,
)