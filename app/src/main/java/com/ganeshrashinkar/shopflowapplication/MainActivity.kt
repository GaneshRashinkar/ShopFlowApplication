package com.ganeshrashinkar.shopflowapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductColor
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.ui.theme.ShopFlowApplicationTheme
import com.ganeshrashinkar.shopflowapplication.util.Resource
import androidx.paging.compose.LazyPagingItems
import com.ganeshrashinkar.shopflowapplication.view.ProductView

class MainActivity : ComponentActivity() {
    val productsViewModels: ProductsViewModel by viewModels {
        ProductsViewModelProviderFactory(application,(application as ApplicationClass).repository)
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShopFlowApplicationTheme {
                var data by remember { productsViewModels.productsState  }
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    if(data is Resource.Loading){
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }else if(data is Resource.Error){
                        Text("Null Data")
                    }
                    else if(data is Resource.Success){
                        data.data?.let {
                            Greeting(it)
                        }
                    }
                    else{
                        Text("Something went wrong")
                    }


                }
            }
        }
    }
}

@Composable
fun Greeting(list: List<ProductsResponseItem>) {
    LazyColumn(modifier = Modifier.background(Color.Black)) {
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
