package com.wheeliechamp.webscraping.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.wheeliechamp.webscraping.R
import com.wheeliechamp.webscraping.WebSrc
import com.wheeliechamp.webscraping.databinding.FragmentWebBinding
import com.wheeliechamp.webscraping.viewmodel.WebViewModel
import kotlinx.android.synthetic.main.fragment_web.view.*

class WebFragment : Fragment() {

    private lateinit var webViewModel: WebViewModel
    private lateinit var binding: FragmentWebBinding

    val websrc = WebSrc.getInstance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false)
        binding.setLifecycleOwner(this)

        val view = binding.root
        view.webView.settings.javaScriptEnabled = true
        view.webView.webViewClient = object : ViewSourceClient(){}
        // ↓ ここの name と (1) は合わせる必要がある
        view.webView.addJavascriptInterface(this, "fragment")
        view.webView.settings.userAgentString = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36"
        view.webView.setInitialScale(80)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())
        val name = sharedPreferences.getString("signature", "")
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webViewModel = ViewModelProvider(this).get(WebViewModel::class.java)
        // 追加
        binding.webviewmodel = webViewModel
        // TODO: Use the ViewModel
    }

    // Fragment表示タイミングで実行
    override fun onStart() {
        super.onStart()
        webViewModel.context = requireContext()
        webViewModel.start()
    }

    open class ViewSourceClient : WebViewClient() {
        override fun onPageFinished(view: WebView, url: String) {
            // (1) window.activity
            view.loadUrl("javascript:window.fragment.viewSource(document.getElementsByTagName('html')[0].outerHTML);")
            Log.d("Test", "onPageFinished")
        }
    }

    @JavascriptInterface
    fun viewSource(src: String) {
        Log.d("Test","viewSource")
        websrc.htmlsrc = src
    }
}