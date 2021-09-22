package com.example.newproejct.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.newproejct.R
import com.example.newproejct.adapter.ShopViewPagerAdapter
import com.example.newproejct.databinding.ActivityShopBinding
import com.google.android.material.tabs.TabLayoutMediator

class ShopActivity : BaseActivity<ActivityShopBinding>(R.layout.activity_shop) {
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

        val list = listOf<String>("메뉴", "정보", "리뷰")
        TabLayoutMediator(tabLayout, viewpager){ tab, position ->
            tab.text = list[position]
        }.attach()

    }
}