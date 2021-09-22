package com.example.newproejct.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newproejct.R
import com.example.newproejct.adapter.MainRecyclerViewAdapter
import com.example.newproejct.data.MainRecyclerviewItem
import com.example.newproejct.databinding.ActivityMainBinding
import com.example.newproejct.snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.newproejct.viewmodel.HomeViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    private val mainViewModel: HomeViewModel by viewModel()
    private val mainRecyclerViewAdapter =  MainRecyclerViewAdapter()

    companion object{
        fun getIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainRcShops.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainRecyclerViewAdapter
        }

        mainRecyclerViewAdapter.itemClickListener = {view, item, position ->
            val message = when (item) {
                is MainRecyclerviewItem.Banner -> "Banner ${position} Clicked"
                is MainRecyclerviewItem.Category -> {
                    "Category ${position} Clicked"
                }
                is MainRecyclerviewItem.Shop -> "Shop ${position} clicked"
            }

            snackbar(message)
            startActivity(Intent(this, ShopActivity::class.java))

        }

        mainViewModel.categoryListItemLiveData.observe(this){ result ->
                mainRecyclerViewAdapter.categoryItems = result
        }

        mainViewModel.shopListItemLiveData.observe(this){ result ->
            mainRecyclerViewAdapter.shopItems = result
        }

        val bannerObserver = Observer<List<MainRecyclerviewItem.Banner>>{ banner ->
            mainRecyclerViewAdapter.bannerItems = banner
        }
        mainViewModel.bannerListItemLiveData.observe(this, bannerObserver)


//        val controller: LayoutAnimationController
//        = AnimationUtils.loadLayoutAnimation(this, R.anim.gridlayout_animation_from_bottom)
//
//        binding.mainRcCategories.layoutAnimation = controller
//        (binding.mainRcCategories.adapter as CategoryAdapter).notifyDataSetChanged()
//        binding.mainRcCategories.scheduleLayoutAnimation()
//



    }
}