package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun ContentScreen(navController: NavController) {
    var loading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(Random.nextDouble(0.7, 1.2).seconds)
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
                        ChipRow()
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
                                            .fillMaxHeight()
                                            .clickable {
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

val images = listOf(
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

@Composable
private fun ChipRow() {
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
            text = "Последние 7 дней",
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