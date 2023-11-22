package com.example.myapplication

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

@Composable
internal fun SettingsScreen(navController: NavController) {
    var loading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(Random.nextDouble(1.0, 1.8).seconds)
        loading = false
    }

    Scaffold(
        topBar = {
            MainToolbar(
                title = "Статистика",
                onBackArrowClick = { navController.popBackStack() },
                image = painterResource(id = R.drawable.ic_info)
            )
        },
        containerColor = MyColors.Surface
    ) {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = FitnessTheme.colors.Gray3,
                    strokeWidth = 1.dp,
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MyColors.Surface)
                    .padding(it)
                    .verticalScroll(rememberScrollState()),
            ) {
                SettingChip()
                Box(
                    modifier = Modifier
                        .background(MyColors.BackgroundChip)
                        .height(1.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = "Обзор",
                    modifier = Modifier.fillMaxWidth(),
                    style = FitnessTheme.textStyles.title
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    text = buildRequiredInputText(),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp,
                    color = Color(0xFF7E7E7E),
                    fontWeight = FontWeight.W400,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "Охваченные аккаунты",
                        fontSize = 14.sp,
                        color = MyColors.Black,
                        fontWeight = FontWeight.W400,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(
                                text = "471 тыс.",
                                fontSize = 13.sp,
                                color = MyColors.Black,
                                fontWeight = FontWeight.W400,
                            )
                            Text(
                                modifier = Modifier.align(Alignment.End),
                                text = "-63,5%",
                                fontSize = 13.sp,
                                color = Color(0xFF7E7E7E),
                                fontWeight = FontWeight.W400,
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.acc_first),
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Вовлеченные аккаунты",
                        fontSize = 14.sp,
                        color = MyColors.Black,
                        fontWeight = FontWeight.W400,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(
                                text = "22,3 тыс.",
                                fontSize = 13.sp,
                                color = MyColors.Black,
                                fontWeight = FontWeight.W400,
                            )
                            Text(
                                modifier = Modifier.align(Alignment.End),
                                text = "-67,3%",
                                fontSize = 13.sp,
                                color = Color(0xFF7E7E7E),
                                fontWeight = FontWeight.W400,
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.acc_first),
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Всего подписчиков",
                        fontSize = 14.sp,
                        color = MyColors.Black,
                        fontWeight = FontWeight.W400,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(
                                text = "254 тыс.",
                                fontSize = 13.sp,
                                color = MyColors.Black,
                                fontWeight = FontWeight.W400,
                            )
                            Text(
                                modifier = Modifier.align(Alignment.End),
                                text = "+0,5%",
                                fontSize = 13.sp,
                                color = MyColors.Green,
                                fontWeight = FontWeight.W400,
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.acc_first),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .background(MyColors.BackgroundChip)
                        .height(6.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(26.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Контент, который вы\nопубликовали",
                        style = FitnessTheme.textStyles.title.copy(
                            fontSize = 18.sp,
                            fontWeight = W600
                        ),
                        lineHeight = 25.sp
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp, bottom = 6.dp),
                        text = "Смотреть\nвсе",
                        style = FitnessTheme.textStyles.title.copy(
                            fontSize = 16.sp,
                            fontWeight = W300,
                            color = MyColors.LightBlue
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(start = 16.dp)
                ) {
                    items(contentList.size) {
                        Image(
                            modifier = Modifier
                                .height(190.dp)
                                .width(95.dp),
                            painter = painterResource(id = contentList[it]),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .clickable { navController.navigate("content") },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Истории",
                        fontSize = 14.sp,
                        color = MyColors.Black,
                        fontWeight = FontWeight.W400,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "76",
                            fontSize = 13.sp,
                            color = MyColors.Black,
                            fontWeight = FontWeight.W400,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.acc_first),
                            contentDescription = null
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Reels",
                        fontSize = 14.sp,
                        color = MyColors.Black,
                        fontWeight = FontWeight.W400,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "1",
                            fontSize = 13.sp,
                            color = MyColors.Black,
                            fontWeight = FontWeight.W400,
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Image(
                            modifier = Modifier.size(15.dp),
                            painter = painterResource(id = R.drawable.acc_first),
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

private fun buildRequiredInputText(): AnnotatedString {
    return buildAnnotatedString {
        append("Число новых подписчиков увеличилось на ")
        withStyle(
            style = SpanStyle(
                color = MyColors.Green
            )
        ) {
            append("1 421")
        }
        append(" по сравнению с периодом 8 ноя - 14 ноя.")
    }
}

private val contentList = listOf(
    R.drawable.content_setting1,
    R.drawable.content_setting2,
    R.drawable.content_setting3,
    R.drawable.content_setting4,
    R.drawable.content_setting5,
)