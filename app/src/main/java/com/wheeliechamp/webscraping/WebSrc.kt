package com.wheeliechamp.webscraping

import android.app.Application

class WebSrc : Application() {

    var htmlsrc: String? = null

    companion object {
        private var instance : WebSrc? = null

        fun  getInstance(): WebSrc {
            if (instance == null)
                instance = WebSrc()
            return instance!!
        }
    }
}