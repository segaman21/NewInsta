package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors

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
    val clicks: String,
    val navigationActions: String,
    val forward: String,
    val exits: String,
    val back: String,
    val next: String,
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
    "14 576",
    "97 153",
    "8 470",
    "5 740",
    "3 213",
    R.drawable.gr_1,
    R.drawable.gr_2
)

@Composable
fun StatsScreen(
    navController: NavController? = null,
    statScreenData: StatScreenData = statScreenData1
) {
    FitnessTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MyColors.Surface
        ) {
            Scaffold(
                topBar = {
                    MainToolbar(
                        title = "Insights",
                        onBackArrowClick = { navController?.popBackStack() }
                    )

                },
                content = { Content(it, statScreenData) },
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
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
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
            Separator(4.dp, Color(0xFFF5F5F5))
            Spacer(modifier = Modifier.size(16.dp))
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
                    Text(text = data.reachedAccounts, style = FitnessTheme.textStyles.title)
                    Text(text = "Охваченные аккаунты", color = Color(0xFFABABAB))
                    StatGraph(
                        subscribers = data.reachedSubscribers,
                        notSubscribers = data.reachedNotSubscribers,
                        graphImage = data.reachGraph
                    )
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
            Separator(size = 4.dp, color = Color(0xFFF5F5F5))
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                InfoText(text = "Вовлеченность")
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(text = data.involvedAccounts, style = FitnessTheme.textStyles.title)
                    Text(text = "Вовлеченные аккаунты", color = Color(0xFFABABAB))
                    StatGraph(
                        subscribers = data.involvedSubscribers,
                        notSubscribers = data.involvedNotSubscribers,
                        graphImage = data.interatcionGragh
                    )
                }

                Separator(color = Color(0xFFF5F5F5))
                TextKeyValueRow(
                    modifier = Modifier.padding(vertical = 16.dp),
                    textKey = "Взаимодействия с историей",
                    textValue = data.interactions,
                    textStyle = FitnessTheme.textStyles.body1
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextKeyValueRow(textKey = "Отметки \"Нравится\"", textValue = data.likes)
                    TextKeyValueRow(textKey = "Ответы", textValue = data.replies)
                    TextKeyValueRow(textKey = "Репосты", textValue = data.reposts)
                    Separator(color = Color(0xFFF5F5F5))
                    TextKeyValueRow(textKey = "Клики по ссылкам", textValue = data.clicks)
                    Separator(color = Color(0xFFF5F5F5))
                    TextKeyValueRow(
                        modifier = Modifier.padding(vertical = 16.dp),
                        textKey = "Навигация",
                        textValue = data.navigationActions,
                        textStyle = FitnessTheme.textStyles.body1
                    )
                    TextKeyValueRow(textKey = "Вперед", textValue = data.forward)
                    TextKeyValueRow(textKey = "Выходы", textValue = data.exits)
                    TextKeyValueRow(textKey = "Назад", textValue = data.back)
                    TextKeyValueRow(textKey = "Следующая история", textValue = data.next)
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
            Separator(4.dp, Color(0xFFF5F5F5))
            Spacer(modifier = Modifier.size(4.dp))
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
            Text(text = subscribers)
            Row {
                Text(text = "Подписчики", color = Color(0xFFABABAB))
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
            modifier = Modifier.padding(horizontal = 16.dp).size(64.dp),
            painter = painterResource(id = graphImage),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = notSubscribers)
            Row {
                Box(
                    Modifier
                        .padding(horizontal = 2.dp)
                        .size(6.dp)
                        .align(CenterVertically)
                        .clip(CircleShape)
                        .background(color = Color(0xFF1A366C))
                )
                Text(text = "Неподписчики", color = Color(0xFFABABAB))
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
private fun Separator(size: Dp = 1.dp, color: Color = Color(0xFFD3D3D3)) {
    Box(
        modifier = Modifier
            .background(color)
            .height(size)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun StatsPreview() {
    StatsScreen()
}