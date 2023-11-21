package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.FitnessTheme
import com.example.myapplication.ui.theme.MyColors

@Composable
internal fun MainToolbar(
    title: String,
    modifier: Modifier = Modifier,
    onBackArrowClick: (() -> Unit)? = null,
    image: Painter? = null,
    @DrawableRes backButtonRes: Int = R.drawable.ic_arrow_left
) {
    Row(
        modifier = modifier
            .background(color = MyColors.Surface)
            .fillMaxWidth()
            .height(62.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            onBackArrowClick?.let {
                IconButton(
                    onClick = it,
                    modifier = Modifier
                ) {
                    Icon(
                        painter = painterResource(id = backButtonRes),
                        contentDescription = null
                    )
                }
            }
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 20.dp),
                style = FitnessTheme.textStyles.title.copy(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.W600
                ),
            )
        }
        image?.let {
            Image(
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 16.dp),
                painter = it,
                contentDescription = null
            )
        }
    }
}

@Composable
internal fun SettingChip() {
    Box(
        Modifier
            .padding(horizontal = 11.dp)
            .padding(vertical= 16.dp)
            .fillMaxWidth(),
    ) {
        Row(
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = FitnessTheme.colors.BackgroundChip)
                .align(Alignment.BottomStart),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Последние 7 дней",
                modifier = Modifier
                    .padding(vertical = 6.dp)
                    .padding(start = 12.dp),
                style = FitnessTheme.textStyles.body3.copy(
                    fontWeight = FontWeight.W800,
                    fontSize = 14.sp
                ),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Image(
                modifier = Modifier
                    .size(13.dp),
                painter = painterResource(id = R.drawable.ic_collapse),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = "15 ноя - 21 ноя",
            modifier = Modifier
                .padding(end = 6.dp)
                .align(Alignment.BottomEnd),
            style = FitnessTheme.textStyles.body3.copy(
                fontWeight = FontWeight.W800,
                fontSize = 14.sp
            ),
        )
    }
}
