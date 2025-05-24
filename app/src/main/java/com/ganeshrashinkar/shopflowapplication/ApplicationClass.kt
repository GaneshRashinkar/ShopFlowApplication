package com.ganeshrashinkar.shopflowapplication

import android.app.Application
import com.ganeshrashinkar.shopflowapplication.repository.ProductsRepository

class ApplicationClass: Application() {
    val repository by lazy { ProductsRepository() }
}