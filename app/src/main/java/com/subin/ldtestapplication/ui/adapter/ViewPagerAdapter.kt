package com.subin.ldtestapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.subin.ldtestapplication.R
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.SeasonNumbers

class ViewPagerAdapter (private val mContext: Context, private val itemList: List<TvShowEntity>) : PagerAdapter() {
    private lateinit var adapter: ItemAdapter
    private var layoutInflater: LayoutInflater? = null
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(mContext)
        val view =    layoutInflater!!.inflate(R.layout.item_viewpage_layout, container, false)
        val recycler: RecyclerView = view.findViewById(R.id.recyclerViewViewPager)
        recycler.layoutManager = LinearLayoutManager(mContext)

            val subList=getSubList(position+1,itemList)
            adapter = ItemAdapter(subList)
            recycler.adapter = adapter



     container.addView(view)
        return view
    }
    override fun getCount(): Int {
        val season=getSeason(mainList = itemList)
        return season.size
    }
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    private fun getSubList(season: Int, list: List<TvShowEntity>): List<TvShowEntity> {

        val subList = arrayListOf<TvShowEntity>()
        list.forEach {
            if (it.season == season) {
                subList.add(it)
            }
        }
        return subList
    }

    private fun getSeason(mainList: List<TvShowEntity>): List<SeasonNumbers> {

        val list = mainList.map {
            SeasonNumbers(it.season!!,false)
        }
        return list.distinct().toList()
    }

}