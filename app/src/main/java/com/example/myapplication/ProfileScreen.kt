package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors
import kotlinx.coroutines.launch

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
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MyColors.Surface)
                ) {
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
                }
            }
            items(contentList.size) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth,
                    painter = painterResource(id = contentList[it]),
                    contentDescription = null
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(4.dp))
            }
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
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
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
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .clickable { onActionNavigate() }
                        .padding(horizontal = 16.dp)
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
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = "Статистика",
                        maxLines = 1,
                        style = FitnessTheme.textStyles.body4.copy(fontSize = 17.sp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
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

private val contentList = listOf(
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
    R.drawable.acc_19,
    R.drawable.acc_20,
    R.drawable.acc_21,
    R.drawable.acc_22,
    R.drawable.acc_23,
    R.drawable.acc_24,
    R.drawable.acc_25,
    R.drawable.acc_26,
    R.drawable.acc_27,
    R.drawable.acc_28,
    R.drawable.acc_29,
    R.drawable.acc_30,
    R.drawable.acc_31,
    R.drawable.acc_32,
    R.drawable.acc_33,
    R.drawable.acc_34,
    R.drawable.acc_35,
    R.drawable.acc_36,
    R.drawable.acc_37,
    R.drawable.acc_38,
    R.drawable.acc_39,
    R.drawable.acc_40,
    R.drawable.acc_41,
    R.drawable.acc_42,
    R.drawable.acc_43,
    R.drawable.acc_44,
    R.drawable.acc_45,
    R.drawable.acc_46,
    R.drawable.acc_47,
    R.drawable.acc_48,
    R.drawable.acc_49,
    R.drawable.acc_50,
    R.drawable.acc_51,
    R.drawable.acc_52,
    R.drawable.acc_53,
    R.drawable.acc_54,
    R.drawable.acc_55,
    R.drawable.acc_56,
    R.drawable.acc_57,
    R.drawable.acc_58,
    R.drawable.acc_59,
    R.drawable.acc_60,
    R.drawable.acc_61,
    R.drawable.acc_62,
    R.drawable.acc_63,
    R.drawable.acc_64,
    R.drawable.acc_65,
    R.drawable.acc_66,
    R.drawable.acc_67,
    R.drawable.acc_68,
    R.drawable.acc_69,
    R.drawable.acc_70,
    R.drawable.acc_71,
    R.drawable.acc_72,
    R.drawable.acc_73,
    R.drawable.acc_74,
    R.drawable.acc_75,
)