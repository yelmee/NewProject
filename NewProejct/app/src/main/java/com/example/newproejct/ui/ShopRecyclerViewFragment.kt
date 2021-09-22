package com.example.newproejct.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.adapter.MenuAdapter
import com.example.newproejct.databinding.FragmentMenuBinding
import com.example.newproejct.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopRecyclerViewFragment(): Fragment() {
    private lateinit var rc: RecyclerView
    val viewModel: ShopViewModel by viewModel()
    private val menuAdapter = MenuAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentMenuBinding.inflate(inflater, container, false)
        rc = view.menuRecyclerview

        rc.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }

        viewModel.requestRepresentMenu.observe(viewLifecycleOwner, Observer { list ->
            menuAdapter.representItem = list
        })

        viewModel.requestAllMenu.observe(viewLifecycleOwner, Observer { allMenu ->
            menuAdapter.groupMenuItem = allMenu
        })
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }
}