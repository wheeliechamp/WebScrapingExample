package com.wheeliechamp.webscraping.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.wheeliechamp.webscraping.R
import com.wheeliechamp.webscraping.databinding.FragmentHomeBinding
import com.wheeliechamp.webscraping.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setLifecycleOwner(this)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity())
        val name = sharedPreferences.getString("signature", "")
        Log.d("Test", "================$name")
        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // 追加
        binding.homeviewmodel = homeViewModel
        // TODO: Use the ViewModel
    }

    // Fragment表示タイミングで実行
    override fun onStart() {
        super.onStart()
        homeViewModel.context = requireContext()
        homeViewModel.start()
    }

}