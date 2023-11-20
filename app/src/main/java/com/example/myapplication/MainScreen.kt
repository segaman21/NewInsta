package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
internal fun MainScreen(
) {


    Scaffold(
        topBar = { Toolbar({}) },
        bottomBar = {
            BottomBar()
        },
        containerColor = MyColors.Surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MyColors.Surface)
                .padding(it)
                .verticalScroll(rememberScrollState())

        ) {
            val qwe = listOf(
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
                R.drawable.test,
            )
            Spacer(modifier = Modifier.height(18.dp))
            Avatar()
            Spacer(modifier = Modifier.height(8.dp))
            AvatarInfo()
            Spacer(modifier = Modifier.height(8.dp))
            Banner()
            Spacer(modifier = Modifier.height(8.dp))
            chip()
            Spacer(modifier = Modifier.height(16.dp))
            Actual()
            Spacer(modifier = Modifier.height(16.dp))
            ContentPanel()

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
            ) {
                qwe.forEach { item ->
                    Image(
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
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val hideModalBottomSheet: () -> Unit = {
        coroutineScope.launch {
            sheetState.hide()
            onSelectGenderDialogDismiss()
        }
    }
//    if (selectTrainingsSpentDialogShowed) {
//        ModalBottomSheet(
//            sheetState = sheetState,
//            onDismissRequest = { hideModalBottomSheet() },
//            contentColor = Color(context.resolveColor(StylesRes.attr.primaryBackgroundLightGray)),
//            containerColor = Color(context.resolveColor(StylesRes.attr.primaryBackgroundLightGray))
//        ) {
//            TrainingsSpentBottomSheetUI(
//                onActionNavigate = onActionNavigate,
//                hideModalBottomSheet = hideModalBottomSheet,
//            )
//        }
//    }
}