package com.example.newproejct.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.newproejct.MyApplication
import com.example.newproejct.R
import com.example.newproejct.adapter.BagRecyclerViewAdapter
import com.example.newproejct.data.bag.BagDataBase
import com.example.newproejct.databinding.ActivityBagBinding
import com.example.newproejct.viewmodel.BagViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BagActivity : BaseActivity<ActivityBagBinding>(R.layout.activity_bag){
    val viewModel: BagViewModel by viewModel()
    val bagRecyclerViewAdapter = BagRecyclerViewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.orderListBagRc.adapter = bagRecyclerViewAdapter
        viewModel.getBag().observe(this, Observer { bagList ->
            bagRecyclerViewAdapter.submitList(bagList)
        })
    }
}