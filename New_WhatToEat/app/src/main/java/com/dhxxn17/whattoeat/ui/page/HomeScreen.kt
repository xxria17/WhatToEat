package com.dhxxn17.whattoeat.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dhxxn17.whattoeat.R
import com.dhxxn17.whattoeat.ui.navigation.Screens

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeContent(navController = navController)
}

@Composable
private fun HomeContent(navController: NavController) {
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "오늘 뭐 먹지? 🍽️",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFF4CC4D), shape = RoundedCornerShape(10.dp))
                    .padding(30.dp)
                    .clickable {
                        navController.navigate(Screens.RouletteScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "랜덤 룰렛 돌리기",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_target),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color(0xFF8B98F8), shape = RoundedCornerShape(10.dp))
                    .padding(30.dp)
                    .clickable {
                        navController.navigate(Screens.WorldcupScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "취향 월드컵으로 고르기",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_thropy),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFEE817E), shape = RoundedCornerShape(10.dp))
                    .padding(30.dp)
                    .clickable {
                        navController.navigate(Screens.LocationScreen.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "내 주변 식당 중에 고르기",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_map),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }

        }
    }
}
