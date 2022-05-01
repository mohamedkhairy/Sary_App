package com.example.sary_app.ui.home

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sary_app.R
import com.example.sary_app.databinding.HomeFragmentBinding
import com.example.sary_app.ui.adapter.CatalogAdapter
import com.example.sary_app.ui.banner.GlideImageLoadingService
import com.example.sary_app.ui.banner.SliderRecyclerViewAdapter
import com.example.sary_app.utils.DataState
import com.example.sary_app.utils.ViewType
import com.example.sary_app.utils.hideView
import com.example.sary_app.utils.showView
import dagger.hilt.android.AndroidEntryPoint
import ss.com.bannerslider.Slider


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    private lateinit var smartLayoutManager: GridLayoutManager
    private lateinit var groupLayoutManager: GridLayoutManager
    private lateinit var bannerLayoutManager: GridLayoutManager

    private lateinit var smartAdapter: CatalogAdapter

    private lateinit var groupAdapter: CatalogAdapter
    private lateinit var bannerAdapter:  CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRefresh()
        getHomeData()
        subscribeBannerObservers()
        subscribeCatalogObservers()
    }

    private fun getHomeData(){
        homeViewModel.getSlideHomeBanner()
        homeViewModel.getHomeCatalog()

    }

    private fun subscribeBannerObservers() {
        homeViewModel.bannerState.observe(viewLifecycleOwner, {
                when (it) {
                    is DataState.Loading -> {
                        showLoading()
                    }
                    is DataState.Success -> {
                        hideLoading()
                        setHomeImageSlider(it.data)
                    }
                    is DataState.Error -> {
                        hideLoading()
                    }
                }
            })
    }


    private fun subscribeCatalogObservers() {
        homeViewModel.catalogState.observe(viewLifecycleOwner, {
            when (it) {
                is DataState.Loading -> {
                    showLoading()
                }
                is DataState.Success -> {

                    hideLoading()
                    hideError()
                    checkView(it.data)
                }
                is DataState.Error -> {
                    hideLoading()
                    showError()
                }
            }
        })
    }


    private fun checkView(viewType: ViewType?){
        when(viewType){
            is ViewType.Smart ->{
                addNewView(viewType.view.showTitle , viewType.view.sectionTitle){
                    smartAdapter = CatalogAdapter(R.layout.smart_layout)
                    adapter = smartAdapter
                    smartLayoutManager = GridLayoutManager(activity, viewType.view.rowCount , LinearLayoutManager.VERTICAL, false)
                    layoutManager = smartLayoutManager
                }
                smartAdapter.items = viewType.view.data
            }

            is ViewType.Group ->{
                addNewView(viewType.view.showTitle , viewType.view.sectionTitle){
                    groupAdapter = CatalogAdapter(R.layout.group_banner_layout)
                    this.adapter = groupAdapter
                    groupLayoutManager = GridLayoutManager(activity, viewType.view.rowCount , LinearLayoutManager.VERTICAL, false)
                    layoutManager = groupLayoutManager
                }
                groupAdapter.items = viewType.view.data
            }

            is ViewType.Banner ->{
                addNewView(viewType.view.showTitle , viewType.view.sectionTitle){
                    bannerAdapter = CatalogAdapter(R.layout.banner_view)
                    this.adapter = bannerAdapter
                    bannerLayoutManager = GridLayoutManager(activity, viewType.view.rowCount , LinearLayoutManager.VERTICAL, false)
                    layoutManager = bannerLayoutManager
                }
                bannerAdapter.items = viewType.view.data
            }
            else -> {

            }
        }
    }


    private inline fun addNewView(showTitle: Boolean ,title: String?, block: RecyclerView.() -> Unit){
        val inflater = requireActivity().getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val child = inflater.inflate(R.layout.base_grid_view, null)
        binding.linearContainer.addView(child)
        if (showTitle && !title.isNullOrBlank()){
            val sectionTitle = child.findViewById<TextView>(R.id.section_title)
            sectionTitle.showView()
            sectionTitle.text = title
        }
        val recycler = child.findViewById<RecyclerView>(R.id.recycler_view)
        block(recycler)
    }

    private fun setHomeImageSlider(data :List<String>?) {
        with(binding.homeImageSlider) {
            data?.let { url ->
                Slider.init(GlideImageLoadingService(context))
                setAdapter(
                    SliderRecyclerViewAdapter(
                        url,
                        requireActivity()
                    )
                )
                setOnSlideClickListener {
                    Toast.makeText(requireContext(), url[it], Toast.LENGTH_SHORT).show()
                }

                setLoopSlides(true)
                setInterval(3000)
            }
        }
    }


    private fun setRefresh() {
        binding.srl.setOnRefreshListener {
            binding.linearContainer.removeAllViewsInLayout()
            getHomeData()
        }
    }

    private fun showLoading(){
        binding.srl.isRefreshing = true
    }

    private fun hideLoading(){
        binding.srl.isRefreshing = false
    }

    private fun showError(){
        binding.error.showView()
    }
    private fun hideError(){
        binding.error.hideView()
    }
}