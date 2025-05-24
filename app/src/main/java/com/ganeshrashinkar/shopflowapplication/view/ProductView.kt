package com.ganeshrashinkar.shopflowapplication.view

import android.app.Activity
import android.content.Context
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganeshrashinkar.shopflowapplication.Greeting
import com.ganeshrashinkar.shopflowapplication.R
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme

@Composable
fun ProductView(product:ProductsResponseItem ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
            .background(Color.Black)
    ) {
        // Load your image using painterResource, remember to add the image to your drawable folder
        val painter = painterResource(id= R.drawable.card_grey_bg_png)
        // Optional: Add content on top of the image using Box with alignment
        Box(
            modifier = Modifier.background(Color.Black),
            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = painter,
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit // or BoxFit depending on your needs
            )
            CardBottom(product)
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

@Composable
@Preview
fun ProductPreview(){
    ShopFlowApplicationTheme {
        val product=ProductsResponseItem(
            api_featured_image = "",
            name = "Vatika Shampoo",
            id = 1,
            description = "Used to wash hair",
            product_colors = listOf(ProductColor("lal","red")),
            product_link = "Shampoo",
            product_type = "Shampoo",
            product_api_url = "url",
            brand = "vatika",
            price = "1",
            rating = 5.1,
            category = "shampoo",
            currency = "Inr",
            tag_list = listOf("tag1","tag2","tag3"),
            website_link = "website",
            created_at = "1 tarkhela",
            image_link = "link",
            price_sign = "Rs.",
            updated_at = "23/05/2025"
        )
        Greeting(listOf(product))
    }
}