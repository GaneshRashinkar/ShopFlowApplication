package com.ganeshrashinkar.shopflowapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme
import com.ganeshrashinkar.shopflowapplication.util.Resource
import com.ganeshrashinkar.shopflowapplication.view.HorizontalSwipeView
import com.ganeshrashinkar.shopflowapplication.view.ProductView
import com.ganeshrashinkar.shopflowapplication.view.SwipeView
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    val productsViewModels: ProductsViewModel by viewModels {
        ProductsViewModelProviderFactory(application,(application as ApplicationClass).repository)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopFlowApplicationTheme {
                var data by remember { productsViewModels.productsState  }
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.primaryColor))
                    .padding(top = 30.dp),
                    topBar = {
                        Row(modifier = Modifier.fillMaxWidth().background(color = colorResource(R.color.primaryColor)), verticalAlignment = Alignment.CenterVertically){
                            Text("Shop",
                            color = Color.White,
                            fontSize = 24.sp,
                                fontFamily = FontFamily.Serif
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton({}) { Icon(imageVector =Icons.Outlined.Search, contentDescription = "Search", tint = Color.White) }
                            IconButton({}) { Icon(imageVector =Icons.Outlined.FavoriteBorder, contentDescription = "Like", tint = Color.White) }
                            IconButton({}) { Icon(imageVector =Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = Color.White) }
                        }
                    }


                ) {
                    if(data is Resource.Loading){
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.primaryColor))){
                            CircularProgressIndicator(color = colorResource(R.color.secondaryColor))
                        }

                    }else if(data is Resource.Error){
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().background(color = colorResource(R.color.primaryColor))) {
                            Text("${data.message}", color = Color.White)
                        }
                    }
                    else if(data is Resource.Success){
                        data.data?.let {

                            Greeting(it)
                        }
                    }
                    else{
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
                        Text("Something went wrong")
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(list: List<ProductsResponseItem>) {
    val categoriesSet=list.map { it.product_type }.toSet()

    LazyColumn(modifier = Modifier
        .background(color = colorResource(R.color.primaryColor))) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            HorizontalSwipeView()
            SkincareCategories(categoriesSet)
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "New Products", color = Color.White  ,
                    fontFamily = FontFamily.Serif, fontSize = 25.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(text = "See All",
                    color =  Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        items(list){
        product->
        ProductView(product)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
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
@Composable
fun SkincareCategories(categoriesSet: Set<String>) {

    val res1= R.drawable.product2_removebg_preview
    val res2= R.drawable.product_image

    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.primaryColor)),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items(categoriesSet.size){
            index->
            val res3= when(index%2==0){
                true-> res1
                false-> res2
            }
            Spacer(modifier = Modifier.width(5.dp))
            CategoryItem(categoriesSet.elementAt(index), res3)
        }

    }
}

@Composable
fun CategoryItem(name: String, imageRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Black)
        )
        Text(
            text = name.replace("_"," ").capitalize(),
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
