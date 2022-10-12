package com.maizhi.mzk.app.ui.main.home

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.common.base.ext.getEmptyView
import com.maizhi.mzk.app.AppConstants
import com.maizhi.mzk.app.R
import com.maizhi.mzk.app.base.BaseFragment
import com.maizhi.mzk.app.data.bean.Banner
import com.maizhi.mzk.app.data.bean.BrandInfo
import com.maizhi.mzk.app.databinding.FragmentHomeBinding
import com.maizhi.mzk.app.databinding.HeaderBannerBinding
import com.youth.banner.indicator.CircleIndicator

class HomeFragment : BaseFragment<HomeModel,FragmentHomeBinding>(R.layout.fragment_home){

    /** 列表总数 */
    private var mTotalCount: Int = 0

    /** 页数 */
    private var mPageNo: Int = 0

    /** 当前列表的数量 */
    private var mCurrentCount: Int = 0

    //轮播图
    private val mBannerList = ArrayList<Banner>()

    private val mBannerAdapter = MyBannerAdapter(mBannerList)

    //商城商标列表
    private val brandAdapter by lazy { BrandAdapter() }

    companion object{
        fun newInstance() = HomeFragment()
    }

    override fun initView() {

        //头部Banner
        val headerBannerBinding = DataBindingUtil.inflate<HeaderBannerBinding>(
            layoutInflater,
            R.layout.header_banner,
            null,
            false
        ).apply {
            banner.apply {
                setAdapter(mBannerAdapter)
                indicator = CircleIndicator(context)
                addBannerLifecycleObserver(viewLifecycleOwner)
            }
        }

        mBinding.apply {

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = brandAdapter
                brandAdapter.apply {
                    loadMoreModule.setOnLoadMoreListener {
                        loadMoreData()
                    }
                    addHeaderView(headerBannerBinding.root)
                    addChildClickViewIds(R.id.price,R.id.call,R.id.cl_item)
                    setOnItemChildClickListener(OnItemChildClickListener { adapter, view, position ->

                        val brandInfo = brandAdapter.data[position]

                        when (view.id){
                            R.id.price -> {

                            }
                            R.id.call -> {

                            }
                            R.id.cl_item ->{

                            }
                        }
                    })
                }
            }

            swipeRefreshLayout.apply {
                setOnRefreshListener { onRefresh() }
            }

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun createObserve() {
        super.createObserve()
        mViewModel.apply {

            bannerListLiveData.observe(viewLifecycleOwner) {
                it?.let {
                    mBannerList.apply {
                        clear()
                        addAll(it)
                    }
                }
                mBannerAdapter.notifyDataSetChanged()
                mBinding.swipeRefreshLayout.isRefreshing = false
            }

            brandListLiveData.observe(viewLifecycleOwner) {
                it?.let { handleArticleData(it) }
            }
        }

    }


    /**下拉刷新 */
    private fun onRefresh() {
        mBinding.swipeRefreshLayout.isRefreshing = true
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        brandAdapter.loadMoreModule.isEnableLoadMore = false
        mViewModel.apply {
            mPageNo = 1
            fetchBanners()
            fetchBrandPageList()
        }
    }

    /** 下拉加载更多 */
    private fun loadMoreData() {
        // 上拉加载时禁止下拉刷新
        mBinding.swipeRefreshLayout.isEnabled = false
        mViewModel.fetchBrandPageList(++mPageNo)
    }


    /**
     * 据处理
     *
     * @param list
     */
    private fun handleArticleData(list: List<BrandInfo>) {

        brandAdapter.apply {
            if(mPageNo == 1){
                if(list.isEmpty()){
                    setEmptyView(recyclerView.getEmptyView())
                }
                setList(list)
            }else{
                addData(list)
            }
            loadMoreModule.apply {
                isEnableLoadMore = true
                if (list.size < AppConstants.PAGE_SIZE) {
                    // 如果加载到的数据不够一页或都已加载完,显示没有更多数据布局,
                    loadMoreEnd(true)
                } else {
                    loadMoreComplete()
                }
                mBinding.swipeRefreshLayout.isEnabled = true
            }
        }
        mBinding.swipeRefreshLayout.isRefreshing = false
    }
}