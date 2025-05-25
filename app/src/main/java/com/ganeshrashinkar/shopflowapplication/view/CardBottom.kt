package com.ganeshrashinkar.shopflowapplication.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganeshrashinkar.shopflowapplication.R
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme
import com.ganeshrashinkar.shopflowapplication.util.Constants.Companion.removeHtmlTags
import com.ganeshrashinkar.shopflowapplication.util.FontFamily.Companion.tangerineFontFamily
import kotlin.random.Random

@Composable
fun CardBottom(product: ProductsResponseItem,modifier:Modifier=Modifier){
    Box(modifier=modifier.padding(16.dp)){
        val painter2= painterResource(id= R.drawable.card_black_shape)
        Image(
            painter = painter2,
            contentDescription = "Background Image",
            modifier = Modifier
                .matchParentSize()

            ,
            contentScale = ContentScale.FillBounds // or BoxFit depending on your needs
        )
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Row {
                Text(text = product.name, color = colorResource(R.color.secondaryColor)  ,
                    fontFamily = tangerineFontFamily, fontSize = 20.sp,
                    modifier = Modifier.weight(1f)
                    )
                Text(text = "In Stock",
                    color = colorResource(R.color.secondaryColor),
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                    )
            }

            Text(text = product.product_type.replace("_"," ").capitalize(), color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = product.description.removeHtmlTags(),
                overflow = TextOverflow.Ellipsis,
                color = Color.White, maxLines = 2,
                modifier = Modifier.width(250.dp),
                fontSize = 14.sp
            )
            Text(text = "Rs. ${product.price}", color = colorResource(R.color.purpleFent), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            RatingStars(Random.nextInt(5))
        }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomEnd)
                    .border(BorderStroke(2.dp, colorResource(R.color.secondaryColor)), shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "Cart",
                    tint = colorResource(R.color.secondaryColor)
                )
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