package com.example.newproejct.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.newproejct.R
import com.example.newproejct.adapter.ShopViewPagerAdapter
import com.example.newproejct.databinding.ActivityShopBinding
import com.example.newproejct.viewmodel.ShopViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopActivity : BaseActivity<ActivityShopBinding>(R.layout.activity_shop) {
    val viewModel: ShopViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagerAdapter = ShopViewPagerAdapter(this)
        pagerAdapter.addFragment(ShopRecyclerViewFragment())
        pagerAdapter.addFragment(ShopRecyclerViewFragment())
        pagerAdapter.addFragment(ShopRecyclerViewFragment())

        val tabLayout = binding.tabLayout
        val viewpager = binding.viewPager

        binding.viewPager?.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.e("jyl","page ${position+1}")
                }
            })
        }

        val tabNm = listOf<String>("메뉴", "정보", "리뷰")
        TabLayoutMediator(tabLayout, viewpager){ tab, position ->
            tab.text = tabNm[position]
        }.attach()

        binding.fab.setOnClickListener {
            startActivity(Intent(this, BagActivity::class.java))
        }

        viewModel.requestShopInfo.observe(this, Observer { data ->
            binding.shop = data
        })
    }
}