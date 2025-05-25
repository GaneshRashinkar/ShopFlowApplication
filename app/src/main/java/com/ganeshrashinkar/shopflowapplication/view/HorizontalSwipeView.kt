package com.ganeshrashinkar.shopflowapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganeshrashinkar.shopflowapplication.R
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme
import com.ganeshrashinkar.shopflowapplication.util.FontFamily.Companion.tangerineFontFamily
import kotlin.random.Random

@Composable
fun HorizontalSwipeView() {
    val pageCount = 3
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
        pageCount = { pageCount }
    )

    HorizontalPager(

        state = pagerState,
    ) { page ->
        // Content for each page
        Box(
            modifier = Modifier

                .background(
//                    when (page) {
//                        0 -> Color.LightGray
//                        1 -> Color.Cyan
//                        2 -> Color.Magenta
//                        else -> Color.Gray
//                    }
                    colorResource(R.color.primaryColor)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
                val painter2= painterResource(id= R.drawable.shopflowcard1)
                Image(
                    painter = painter2,
                    contentDescription = "Background Image",
                    modifier = Modifier
                        .matchParentSize()
                        .padding(16.dp),
                     contentScale = ContentScale.FillBounds // or BoxFit depending on your needs
                )
                Column(
                    modifier = Modifier.padding(30.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "GET 20% OFF",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Get 20% off",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .background(colorResource(R.color.secondaryColor), shape = RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "12-16 June",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
Spacer(modifier = Modifier.height(40.dp))
                }
        }
    }
}

@Composable
@Preview
fun HorizontalSwipePrevies(){
    ShopFlowApplicationTheme {
        HorizontalSwipeView()
    }
}


