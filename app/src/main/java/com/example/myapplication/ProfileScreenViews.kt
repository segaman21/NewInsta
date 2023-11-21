package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors

@Composable
internal fun Toolbar(
    onSettingClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "dashtolc",
                style = FitnessTheme.textStyles.title
            )
            Spacer(modifier = Modifier.width(6.dp))
            Image(
                modifier = Modifier.size(10.dp),
                painter = painterResource(id = R.drawable.ic_collapse),
                contentDescription = null
            )
        }
        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                modifier = Modifier.size(27.dp),
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(24.dp))
            Image(
                painter = painterResource(id = R.drawable.setting),
                modifier = Modifier
                    .size(26.dp)
                    .clickable(onClick = { onSettingClick() }),
                contentDescription = null
            )
        }

    }
}

@Composable
internal fun Avatar(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(95.dp),

            painter = painterResource(id = R.drawable.avatar_new),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "327",
                    maxLines = 1,
                    style = FitnessTheme.textStyles.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Публика...",
                    style = FitnessTheme.textStyles.body,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "254 т...",
                    maxLines = 1,
                    style = FitnessTheme.textStyles.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Подписч...",
                    style = FitnessTheme.textStyles.body,
                    maxLines = 1,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "30",
                    maxLines = 1,
                    style = FitnessTheme.textStyles.body1
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Подписки",
                    style = FitnessTheme.textStyles.body,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
internal fun AvatarInfo(
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Инстадива Дарья (Даштольц)",
            style = FitnessTheme.textStyles.body3,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "«Богиня»- в инстаграме \uD83E\uDD11",
                style = FitnessTheme.textStyles.body4,
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Text(
                text = "Чемпионка России по парусному спорту - в жизни ⛵",
                style = FitnessTheme.textStyles.body4,
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row {
            Text(
                text = "Провожу «Дивные яхт-фи",
                style = FitnessTheme.textStyles.body4,
            )
            Text(
                text = "... ещё",
                style = FitnessTheme.textStyles.body4.copy(
                    color = MyColors.Gray3,
                ),
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.link2),
                contentDescription = null,
            )
            Text(
                text = "www.youtube.com/watch?v=|-ryBLoz|_Q",
                style = FitnessTheme.textStyles.body4.copy(
                    color = Color(0xFF435F7E), fontWeight = FontWeight.W500,
                ),
            )
        }
    }
}

@Composable
internal fun BottomBar(
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 10.dp)
            .height(55.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.home),
            contentDescription = null,
        )
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.bottom_plus),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.play),
            contentDescription = null
        )
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.image),
            contentDescription = null
        )
    }
}

@Composable
internal fun Banner() {
    Column(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = FitnessTheme.colors.BackgroundChip)
    ) {
        Text(
            text = "Профессиональная панель",
            modifier = Modifier
                .padding(top = 14.dp)
                .padding(horizontal = 14.dp),
            style = FitnessTheme.textStyles.body3.copy(fontWeight = W600),
        )
        Text(
            text = "За последние 30 дней вы охватили 2,3 млн аккаунтов",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(bottom = 14.dp)
                .padding(horizontal = 14.dp),
            style = FitnessTheme.textStyles.body4.copy(
                color = MyColors.Gray3,
                fontSize = 14.sp
            ),
        )
    }
}

@Composable
internal fun Chip() {
    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .weight(1f)
        ) {
            Text(
                text = "Редактировать профиль",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = FitnessTheme.textStyles.body3.copy(fontWeight = W600),
            )
        }
        Column(
            Modifier
                .padding(start = 8.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .weight(1f)
        ) {
            Text(
                text = "Поделиться профилем",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = FitnessTheme.textStyles.body3.copy(fontWeight = W600),
            )
        }
    }
}

@Composable
internal fun Actual() {
    val actualList = listOf(
        R.drawable.actual_first,
        R.drawable.act_second,
        R.drawable.act_3,
        R.drawable.act_4,
        R.drawable.act_5,
        R.drawable.act_6,
        R.drawable.act_7,
        R.drawable.act_8,
        R.drawable.act_9,
    )
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(actualList.size) {
            Image(
                modifier = Modifier.size(95.dp),
                painter = painterResource(id = actualList[it]),
                contentDescription = null
            )
        }
    }
}

@Composable
internal fun ContentPanel() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.first_panel),
            modifier = Modifier
                .height(50.dp)
                .weight(1f),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.second_panel),
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.third_panel),
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            contentDescription = null
        )
    }
}
