package com.example.newproejct.adapter

import android.os.Message
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.newproejct.R
import com.example.newproejct.data.MainRecyclerviewItem
import com.example.newproejct.databinding.*

sealed class MainRecyclerViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {

    val categoryRecyclerViewAdapter = CategoryAdapter()
    var itemClickListener: ((view: View, item: MainRecyclerviewItem, position: Int) -> Unit)? = null

    class BannerAndCategoryViewHolder(
        private val bindingHeader: HeaderMainBinding
    ) : MainRecyclerViewHolder(bindingHeader) {

        private var currentPosition = Int.MAX_VALUE / 2
        private var intervalTime = 1500.toLong()
        private var myHandler = MyHandler()
        private var bannerSize = 0

        fun bind(
            banner: List<MainRecyclerviewItem.Banners>,
            category: List<MainRecyclerviewItem.Categories>
        ) {
            bannerSize = banner.size
            // init adapter
            bindingHeader.mainRcCategories.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, 5)
                adapter = categoryRecyclerViewAdapter
            }
            categoryRecyclerViewAdapter.submitList(category)

            val adapter = BannerViewPagerAdapter(banner)
            bindingHeader.mainViewPager.apply {
                setAdapter(adapter)
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                setCurrentItem(currentPosition % bannerSize, false)
                Log.d("jyl","bannerSize:"+currentPosition % bannerSize)

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(state: Int) {
                        super.onPageSelected(state)
                        //배너 텍스트 변경

                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        super.onPageScrollStateChanged(state)
                        when (state) {
                            ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                            ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                        }
                    }
                })
            }

            // item click animation event
            categoryRecyclerViewAdapter.itemClickListener = {view, item, position ->
                    val message = when (item) {
                        is MainRecyclerviewItem.Banners -> "Banner ${position} Clicked"
                        is MainRecyclerviewItem.Categories -> "Category ${position} Clicked"
                        is MainRecyclerviewItem.Shop -> "Shop ${position} clicked"
                    }
                val animation: Animation = AnimationUtils.loadAnimation(view.context, R.anim.bigsmall)
                view.startAnimation(animation)
            }
        }

        private fun autoScrollStart(intervalTime: Long) {
            myHandler.removeMessages(0)
            myHandler.sendEmptyMessageDelayed(0, intervalTime)
        }


        private fun autoScrollStop() {
            myHandler.removeMessages(0)
        }

        private inner class MyHandler : android.os.Handler() {
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)

                if (msg.what == 0) {
                    bindingHeader.mainViewPager.setCurrentItem(++currentPosition % bannerSize, true)
                    Log.d("jyl","bannerSize:"+currentPosition % bannerSize)
                    autoScrollStart(intervalTime)
                }

            }
        }
    }

    class ShopViewHolder(private val binding: ItemRcShopBinding) : MainRecyclerViewHolder(binding) {
        fun bind(shop: MainRecyclerviewItem.Shop) {
            binding.image = shop.url
            binding.title = shop.name
            binding.subTitle = shop.cat[0].toString()
            binding.time = shop.time
            binding.executePendingBindings()

             binding.itemShopIv.setOnClickListener {
                 itemClickListener?.invoke(it, shop, absoluteAdapterPosition)
             }
        }
    }
}