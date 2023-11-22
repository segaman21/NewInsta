package com.example.myapplication

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

data class StatScreenData(
    @DrawableRes val previewImage: Int,
    val previewText: String,
    val reachedAccounts: String,
    val involvedAccounts: String,
    val profileActions: String,
    val reachedSubscribers: String,
    val reachedNotSubscribers: String,
    val shows: String,
    val involvedSubscribers: String,
    val involvedNotSubscribers: String,
    val interactions: String,
    val likes: String,
    val replies: String,
    val reposts: String,
    val clicks: String?,
    val navigationActions: String,
    val forward: String,
    val exits: String,
    val back: String,
    val next: String,
    val actionInProfile: String,
    val comesProfile: String,
    val subscriber: String,
    @DrawableRes val reachGraph: Int,
    @DrawableRes val interatcionGragh: Int,
)

val statScreenData1 = StatScreenData(
    R.drawable.preview,
    "15 ноября в 4:10",
    "105 756",
    "392",
    "133",
    "98 308",
    "7 448",
    "105 756",
    "385",
    "7",
    "427",
    "273",
    "105",
    "49",
    "14 000",
    "114 576",
    "97 153",
    "8 470",
    "5 740",
    "3 213",
    actionInProfile = "133",
    comesProfile = "133",
    subscriber = "0",
    R.drawable.gr_1,
    R.drawable.gr_2
)

val statScreenData2 = StatScreenData(
    R.drawable.preview2,
    "15 ноября в 4:08",
    "110 047",
    "455",
    "196",
    "102 515",
    "7 532",
    "110 047",
    "441",
    "14",
    "504",
    "301",
    "189",
    "14",
    null,
    "116 130",
    "97 930",
    "8 729",
    "5 173",
    "4 298",
    actionInProfile = "196",
    comesProfile = "196",
    subscriber = "0",
    R.drawable.gr_1,
    R.drawable.gr_3
)

const val KEY = "KEY"

@Composable
fun StatsScreen(
    navController: NavController,
    statScreenData: StatScreenData = statScreenData1
) {
    var loading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(Random.nextDouble(1.4, 2.0).seconds)
        loading = false
    }

    BackHandler {
        navController.previousBackStackEntry?.savedStateHandle?.set(KEY,true)
        navController.popBackStack()
    }

    FitnessTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MyColors.Surface
        ) {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier
                            .background(color = MyColors.Surface)
                            .fillMaxWidth()
                            .height(62.dp),
                    ) {
                        Image(
                            modifier = Modifier
                                .size(26.dp)
                                .clickable {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(KEY,true)
                                    navController.popBackStack()
                                }
                                .align(CenterStart)
                                .padding(start = 12.dp),
                            painter = painterResource(id = R.drawable.backk),
                            contentDescription = null
                        )
                        Text(
                            text = "Insights",
                            modifier = Modifier.align(Center),
                            textAlign = TextAlign.Center,
                            style = FitnessTheme.textStyles.title.copy(
                                fontSize = 15.sp,
                                fontWeight = W700
                            )
                        )
                    }
                },
                content = {
                    if (loading) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Center),
                                color = FitnessTheme.colors.Gray3,
                                strokeWidth = 1.dp,
                            )
                        }
                    } else {
                        Content(it, statScreenData)
                    }
                },
                bottomBar = { BottomBar() },
                containerColor = MyColors.Surface
            )
        }
    }
}

@Composable
private fun Content(paddingValues: PaddingValues, data: StatScreenData) {
    Column(
        Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Separator()
        Image(
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            painter = painterResource(id = data.previewImage),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = data.previewText,
            style = FitnessTheme.textStyles.body.copy(
                fontWeight = W500,
                color = Color(0xFFABABAB),
                fontSize = 12.sp
            )
        )
        Column {
            Column(Modifier.padding(16.dp)) {
                InfoText("Обзор")
                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    TextKeyValueRow(
                        textKey = "Охваченные аккаунты",
                        textValue = data.reachedAccounts
                    )
                    TextKeyValueRow(
                        textKey = "Вовлеченные аккаунты",
                        textValue = data.involvedAccounts
                    )
                    TextKeyValueRow(textKey = "Действия в профиле", textValue = data.profileActions)
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Separator(6.dp, Color(0xFFF5F5F5))
            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                InfoText("Охват")
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.offset(x = (-4).dp),
                        text = data.reachedAccounts,
                        style = FitnessTheme.textStyles.title,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        modifier = Modifier.offset(x = (-4).dp),
                        text = "Охваченные аккаунты",
                        color = Color(0xFFABABAB),
                        fontSize = 13.sp
                    )
                    StatGraph(
                        subscribers = data.reachedSubscribers,
                        notSubscribers = data.reachedNotSubscribers,
                        graphImage = data.reachGraph
                    )
                    Spacer(modifier = Modifier.size(16.dp))

                }
                Spacer(modifier = Modifier.size(8.dp))
                Separator(color = Color(0xFFF5F5F5))
                TextKeyValueRow(
                    modifier = Modifier.padding(vertical = 16.dp),
                    textKey = "Показы",
                    textValue = data.shows,
                    textStyle = FitnessTheme.textStyles.body1
                )
            }
            Separator(size = 6.dp, color = Color(0xFFF5F5F5))
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.size(10.dp))
                InfoText(text = "Вовлеченность")
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = data.involvedAccounts, style = FitnessTheme.textStyles.title,
                        modifier = Modifier.offset(x = (-6).dp)
                    )
                    Text(
                        text = "Вовлеченные аккаунты", color = Color(0xFFABABAB),
                        modifier = Modifier.offset(x = (-5).dp),
                    )
                    StatGraph(
                        subscribers = data.involvedSubscribers,
                        notSubscribers = data.involvedNotSubscribers,
                        graphImage = data.interatcionGragh
                    )
                    Spacer(modifier = Modifier.size(18.dp))

                }

                Separator(color = Color(0xFFF5F5F5))
                TextKeyValueRow(
                    modifier = Modifier.padding(vertical = 20.dp),
                    textKey = "Взаимодействия с историей",
                    textValue = data.interactions,
                    textStyle = FitnessTheme.textStyles.body1
                )
                Column() {
                    TextKeyValueRow(textKey = "Отметки \"Нравится\"", textValue = data.likes)
                    Spacer(modifier = Modifier.height(22.dp))
                    TextKeyValueRow(textKey = "Ответы", textValue = data.replies)
                    Spacer(modifier = Modifier.height(22.dp))
                    TextKeyValueRow(textKey = "Репосты", textValue = data.reposts)
                    Spacer(modifier = Modifier.height(30.dp))

                    data.clicks?.let {
                        Separator(color = Color(0xFFF5F5F5))
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Клики по ссылкам", textValue = it)
                        Spacer(modifier = Modifier.height(22.dp))
                    }

                    Separator(color = Color(0xFFF5F5F5))
                    TextKeyValueRow(
                        modifier = Modifier.padding(vertical = 20.dp),
                        textKey = "Навигация",
                        textValue = data.navigationActions,
                        textStyle = FitnessTheme.textStyles.body1
                    )
                    if (data.clicks != null) {
                        TextKeyValueRow(textKey = "Вперед", textValue = data.forward)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Выходы", textValue = data.exits)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Назад", textValue = data.back)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Следующая история", textValue = data.next)
                        Spacer(modifier = Modifier.height(35.dp))
                    } else {
                        TextKeyValueRow(textKey = "Вперед", textValue = data.forward)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Назад", textValue = data.back)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Следующая история", textValue = data.next)
                        Spacer(modifier = Modifier.height(22.dp))
                        TextKeyValueRow(textKey = "Выходы", textValue = data.exits)
                        Spacer(modifier = Modifier.height(35.dp))
                    }
                }
            }
            Separator(6.dp, Color(0xFFF5F5F5))
            Column(Modifier.padding(14.dp)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = CenterVertically) {
                        Text(text = "Действия в профиле", style = FitnessTheme.textStyles.body1)
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(20.dp),
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.info_png),
                        )
                    }
                    Text(text = data.actionInProfile, style = FitnessTheme.textStyles.body1)
                }
                Column(
                    Modifier.fillMaxWidth(),
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    TextKeyValueRow(
                        textKey = "Посещение профиля",
                        textValue = data.comesProfile
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    TextKeyValueRow(
                        textKey = "Подписки",
                        textValue = data.subscriber
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun StatGraph(
    subscribers: String,
    notSubscribers: String,
    @DrawableRes graphImage: Int
) {
    Row(
        Modifier.padding(vertical = 16.dp),
        verticalAlignment = CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(text = subscribers, style = FitnessTheme.textStyles.title, fontSize = 14.sp)
            Row {
                Text(
                    text = "Подписчики",
                    color = Color(0xFFABABAB),
                    fontSize = 12.sp,
                    fontWeight = W400
                )
                Box(
                    Modifier
                        .padding(horizontal = 2.dp)
                        .size(6.dp)
                        .align(CenterVertically)
                        .clip(CircleShape)
                        .background(color = Color(0xFF5D91EA))
                )
            }
        }
        Image(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .size(64.dp),
            painter = painterResource(id = graphImage),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = notSubscribers, style = FitnessTheme.textStyles.title, fontSize = 14.sp)
            Row {
                Box(
                    Modifier
                        .padding(horizontal = 2.dp)
                        .size(6.dp)
                        .align(CenterVertically)
                        .clip(CircleShape)
                        .background(color = Color(0xFF1A366C))
                )
                Text(
                    text = "Неподписчики",
                    color = Color(0xFFABABAB),
                    fontSize = 12.sp,
                    fontWeight = W400
                )
            }
        }
    }
}

@Composable
private fun TextKeyValueRow(
    modifier: Modifier = Modifier,
    textKey: String,
    textValue: String,
    textStyle: TextStyle = FitnessTheme.textStyles.body
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = textKey, style = textStyle)
        Text(text = textValue, style = textStyle)
    }
}

@Composable
private fun InfoText(text: String) {
    Row(
        Modifier.padding(vertical = 16.dp),
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = text,
            style = FitnessTheme.textStyles.title.copy(
                fontSize = 18.sp,
                fontWeight = W600
            )
        )
        Spacer(modifier = Modifier.width(6.dp))
        Image(
            modifier = Modifier.size(20.dp),
            contentDescription = null,
            painter = painterResource(id = R.drawable.info_png),
        )
    }
}

@Composable
internal fun Separator(size: Dp = 1.dp, color: Color = Color(0xFFD3D3D3)) {
    Box(
        modifier = Modifier
            .background(color)
            .height(size)
            .fillMaxWidth()
    )
}
