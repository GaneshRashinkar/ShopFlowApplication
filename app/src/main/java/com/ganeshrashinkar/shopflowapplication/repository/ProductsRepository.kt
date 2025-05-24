package com.ganeshrashinkar.shopflowapplication.repository

import com.ganeshrashinkar.newsapplicationwithmvvm.api.RetrofitInstance
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponse
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import okhttp3.Response

class ProductsRepository {
suspend fun getProducts() = RetrofitInstance.api.getProducts()
}