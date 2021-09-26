package com.example.newproejct.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.adapter.MenuAdapter
import com.example.newproejct.data.bag.Bag
import com.example.newproejct.databinding.FragmentGroupMenuBinding
import com.example.newproejct.viewmodel.BagViewModel
import com.example.newproejct.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopRecyclerViewFragment(): Fragment() {
    private lateinit var rc: RecyclerView
    val viewModel: ShopViewModel by viewModel()
    val bagViewModel: BagViewModel by viewModel()
    private val menuAdapter = MenuAdapter()

    private lateinit var myContext: Context

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        if (rootView == null) {
//            rootView = inflater.inflate(R.layout.fragment_menu, null)
//        }else{
//            (container?.parent as ViewGroup).removeView(rootView)
//        }
//        return rootView

//        if (container != null) {
//            myContext = container.context
//        }


        val view = FragmentGroupMenuBinding.inflate(inflater, container, false)
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
        myContext = view.context

        menuAdapter.toastListener = { item ->
            Toast.makeText(myContext, "장바구니에 담겼습니다.", Toast.LENGTH_LONG).show()
            val bag = Bag(item.name, item.menuPrices[0].price, 1)
            bagViewModel.addBag(bag)
        }
    }
}