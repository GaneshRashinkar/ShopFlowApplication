package com.ganeshrashinkar.shopflowapplication

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ganeshrashinkar.shopflowapplication.remote.responseModel.ProductsResponseItem
import com.ganeshrashinkar.shopflowapplication.repository.ProductsRepository
import com.ganeshrashinkar.shopflowapplication.util.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class ProductsViewModel(val application:Application,val productsRepository: ProductsRepository): ViewModel() {
    var productsLiveData:Resource<List<ProductsResponseItem>>? = Resource.Loading()
    val productsState:MutableState<Resource<List<ProductsResponseItem>>> = mutableStateOf(Resource.Loading())
    init {
        getProducts()
    }
    fun getProducts()=viewModelScope.launch {
        safeProductsCall()
    }

    private suspend fun safeProductsCall(){
        productsState.value=Resource.Loading()
        try {
            if(hasInternetConnection()){
            val response=productsRepository.getProducts()
                productsState.value=response.body()?.let { Resource.Success(it.toList()) }!!
            }
            else{
                productsState.value=Resource.Error("No Internet connection")
            }
        }catch (t:Throwable){
            when(t){
                is IOException ->{
                    productsState.value=Resource.Error("Network Failure")
                }
                else-> {
                    productsState.value=Resource.Error("Conversion Error")
                }
            }
        }
    }
    private fun hasInternetConnection():Boolean{
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val activeNetwork=connectivityManager.activeNetwork?: return false
        val capabilities=connectivityManager.getNetworkCapabilities(activeNetwork)?:return false
        return when{
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else->false
        }
    }
}

class ProductsViewModelProviderFactory(val application: Application, val productssRepository: ProductsRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductsViewModel::class.java)){
            return ProductsViewModel(application,productssRepository) as T
        }
        throw ClassCastException("class cast exception")
    }
}