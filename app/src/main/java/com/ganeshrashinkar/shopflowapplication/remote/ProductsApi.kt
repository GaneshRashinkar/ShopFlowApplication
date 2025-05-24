package com.ganeshrashinkar.shopflowapplication.api


import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products.json")
    suspend fun getProducts(
    ):Response<ProductsResponse>


}