package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyColors

@Composable
internal fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            MainToolbar(
                title = "Статистика",
                onBackArrowClick = { navController.popBackStack() })
        },
        containerColor = MyColors.Surface
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MyColors.Surface)
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            SettingChip()
        }
    }

}