package com.ganeshrashinkar.shopflowapplication.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ganeshrashinkar.shopflowapplication.R
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme

@Composable
fun CardBottom(product: ProductsResponseItem,modifier:Modifier=Modifier){
    Box(){
        val painter2= painterResource(id= R.drawable.card_black_shape)
        Image(
            painter = painter2,
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 20.dp)
            ,
            contentScale = ContentScale.FillWidth // or BoxFit depending on your needs
        )
        Column(
            modifier = Modifier.padding(30.dp)
        ) {
            Row {
                Text(text = product.name, color = Color.White)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "In Stock", color = Color.White)
            }

            Text(text = product.product_type.replace("_"," ").capitalize(), color = Color.White)
            Text(text = product.description.substringBefore("."), color = Color.White, maxLines = 2)
            Text(text = "Rs. ${product.price}", color = Color.White)
        }
    }
}

@Composable
@Preview
fun CardBottomPreview(){
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
    CardBottom(product)
}