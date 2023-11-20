package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun ProfileScreen(
    navController: NavController
) {
    var openBottomSheet by remember { mutableStateOf(false) }

    TrainingsSpentBottomSheetUI(
        selectTrainingsSpentDialogShowed = openBottomSheet,
        onSelectGenderDialogDismiss = { openBottomSheet = false },
        onActionNavigate = {
            openBottomSheet = false
            navController.navigate("settings")
        }
    )

    Scaffold(
        topBar = { Toolbar { openBottomSheet = true } },
        bottomBar = { BottomBar() },
        containerColor = MyColors.Surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MyColors.Surface)
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            val contentList = listOf(
                R.drawable.acc_1,
                R.drawable.acc_2,
                R.drawable.acc_3,
                R.drawable.acc_4,
                R.drawable.acc_5,
                R.drawable.acc_6,
                R.drawable.acc_7,
                R.drawable.acc_8,
                R.drawable.acc_9,
                R.drawable.acc_10,
                R.drawable.acc_11,
                R.drawable.acc_12,
                R.drawable.acc_13,
                R.drawable.acc_14,
                R.drawable.acc_15,
                R.drawable.acc_16,
                R.drawable.acc_17,
                R.drawable.acc_18,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Avatar()
            Spacer(modifier = Modifier.height(8.dp))
            AvatarInfo()
            Spacer(modifier = Modifier.height(8.dp))
            Banner()
            Spacer(modifier = Modifier.height(8.dp))
            Chip()
            Spacer(modifier = Modifier.height(16.dp))
            Actual()
            Spacer(modifier = Modifier.height(16.dp))
            ContentPanel()
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                contentList.forEach { item ->
                    Image(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(id = item),
                        contentDescription = null
                    )
                }
            }

//            LazyVerticalGrid(
//                columns = GridCells.Fixed(3),
//                modifier = Modifier.fillMaxWidth(),
//            ) {
//                items(qwe.size) {
//                    Image(
//                        modifier = Modifier.fillMaxSize(),
//                        painter = painterResource(id = qwe[it]),
//                        contentDescription = null
//                    )
//                }
//            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrainingsSpentBottomSheetUI(
    selectTrainingsSpentDialogShowed: Boolean,
    onActionNavigate: () -> Unit,
    onSelectGenderDialogDismiss: () -> Unit,
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
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_1),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Настройки и конфиденциальность",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Image(
                            modifier = Modifier.size(27.dp),
                            painter = painterResource(id = R.drawable.sett_2),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Threads",
                            maxLines = 1,
                            style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = MyColors.LightBlue),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                            text = "Новинка!",
                            style = FitnessTheme.textStyles.body3.copy(
                                fontSize = 11.sp,
                                color = MyColors.Surface
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .clickable { onActionNavigate() }
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_3),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Статистика",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_4),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Ваши действия",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_5),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Архив",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_6),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "QR-код",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_7),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Сохраненное",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_8),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Родительский контроль",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_9),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Заказы и платежи",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_10),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Близкие друзья",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_11),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Избранное",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(27.dp),
                        painter = painterResource(id = R.drawable.sett_12),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Интересные люди",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Spacer(Modifier.navigationBarsPadding())
        }
    }
}