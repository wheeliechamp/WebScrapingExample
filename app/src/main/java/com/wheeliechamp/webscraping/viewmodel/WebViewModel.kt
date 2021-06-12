package com.wheeliechamp.webscraping.viewmodel

import android.content.Context
import android.util.Log
import android.webkit.WebView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wheeliechamp.webscraping.WebSrc
import com.wheeliechamp.webscraping.model.User
import com.wheeliechamp.webscraping.room.UserDao
import com.wheeliechamp.webscraping.room.UserDatabase
import com.wheeliechamp.webscraping.view.WebFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class WebViewModel : ViewModel() {

    lateinit var mUserDao: UserDao
    lateinit var context: Context

    var count: Int = 0
    var isRun: Boolean = true
    var url = MutableLiveData<String>()
    val websrc = WebSrc.getInstance()

    init {
        Log.d("Test", "Init")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text
    val countText = MutableLiveData<String>("MutableLiveData")

    fun start() {
    }

    fun btnStartClick(wv: WebView) {
        Log.d("Test", "btnStartClick!!!!")
        wv.loadUrl("https://yahoo.co.jp")

        viewModelScope.launch() {
            while(isRun) {
                countText.value = count.toString()
                delay(1000L)
                count++
            }
            Log.d("Test", "viewModelScope")
        }
        //mUserDao = UserDatabase.getInstance(context).userDao()
        //insertUser()
    }

    fun btnStopClick() {
        isRun = false
        Log.d("Test", "btnStopClick!!!!")
        //Log.d("Test", websrc.htmlsrc.toString())
    }

    private fun insertUser() {
        val newUser = User(0, "Taro", "Yamada")
        mUserDao.insertAll(newUser)
        Log.d("Test", mUserDao.getAll().toString())
    }

    @BindingAdapter("loadUrl")
    fun loadUrlString(view: WebView, url: String?) {
        view.loadUrl(url!!)
    }

}
