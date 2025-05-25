package com.ganeshrashinkar.shopflowapplication.util

class Constants {
    companion object{
        const val BASE_URL="https://makeup-api.herokuapp.com/api/v1/"
        fun String.removeHtmlTags(): String {
            return this.replace("<[^>]*>".toRegex(), "")
        }
    }
}