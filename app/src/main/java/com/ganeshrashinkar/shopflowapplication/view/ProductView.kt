package com.ganeshrashinkar.shopflowapplication.view

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ganeshrashinkar.shopflowapplication.Greeting
import com.ganeshrashinkar.shopflowapplication.R
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme
import kotlin.random.Random

@Composable
fun ProductView(product:ProductsResponseItem ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
            .padding(top = 20.dp)
            .background(color = colorResource(R.color.primaryColor))
    ) {
        // Load your image using painterResource, remember to add the image to your drawable folder
        val painter = painterResource(id= R.drawable.card_grey_bg_png)
        val painter2=painterResource(id= R.drawable.product2_removebg_preview)
        val painter3=painterResource(id= R.drawable.product_image)
        val painter1= when(Random.nextInt()%2==0){
            true-> painter2
            false-> painter3
        }
        // Optional: Add content on top of the image using Box with alignment
        Box(
            modifier = Modifier.background(color = colorResource(R.color.primaryColor)),
            contentAlignment = Alignment.BottomStart
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .align(Alignment.TopStart),
                contentAlignment = Alignment.Center,
            ){
                Icon(imageVector = Icons.Outlined.Favorite,
                    contentDescription = "favorite",
                    tint = colorResource(R.color.purpleFent)
                    )
            }

            Image(
                painter = painter,
                contentDescription = "Background Image",
                modifier  = Modifier
                    .matchParentSize(),
                contentScale = ContentScale.FillBounds // or BoxFit depending on your needs
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                AsyncImage(
//                    model = product.image_link?:painter1,
//                    contentDescription = product.name,
//                    modifier=Modifier
//                        .height(250.dp),
//                    contentScale = ContentScale.Fit,
//                    placeholder = painter1
//                )
                Image(
                    painter = painter1,
                    contentDescription = "Background Image",
                    modifier = Modifier.weight(1f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.BottomCenter
                )
                CardBottom(product, modifier = Modifier.padding(bottom = 20.dp))

            }

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
        ProductView(product)
    }
}