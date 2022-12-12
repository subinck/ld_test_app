package com.subin.ldtestapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.subin.ldtestapplication.R
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.SeasonNumbers
import com.subin.ldtestapplication.data.models.TVShowResponseItem
import com.subin.ldtestapplication.databinding.ActivityMainBinding
import com.subin.ldtestapplication.ui.adapter.ItemAdapter
import com.subin.ldtestapplication.ui.adapter.NumberPickerAdapter
import com.subin.ldtestapplication.ui.adapter.ViewPagerAdapter
import com.subin.ldtestapplication.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemAdapter
    private lateinit var adapterHorizontal: NumberPickerAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var mainList: List<TvShowEntity> = arrayListOf()
    private var mList: List<SeasonNumbers> = arrayListOf()
    private var season: Int = 1
    private lateinit var viewPager:ViewPager
    private var isViewed:Boolean=false
    private lateinit var mViewPagerAdapter:ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
        initViewModel()
    }

    private fun initViews() {
        binding.listItemSeason.text = "Season : 1"
           binding .recyclerViewHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        viewPager = findViewById(R.id.viewpager)
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.clipToPadding = false
        viewPager.pageMargin = 25



    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.getFromDB().observe(this) { dbdata ->

            if (dbdata.isNotEmpty()) {
                mainList = dbdata
                initHorizontalList(mainList,1)
               // adapter = ItemAdapter(subList)
                mViewPagerAdapter = ViewPagerAdapter(this, mainList,0)
                viewPager.adapter = mViewPagerAdapter
               // binding.recyclerView1.adapter = adapter
            } else {
                viewModel.getLiveDataObserver().observe(this) { response ->
                    if (response != null) {
                        val list: ArrayList<TVShowResponseItem> = response
                        val entityList: ArrayList<TvShowEntity> = arrayListOf()
                        for (i in list) {
                            val airDate = i.airdate
                            val id = i.id
                            val original = i.image.original
                            val name = i.name
                            val number = i.number
                            val season = i.season
                            val summary = i.summary
                            val tvShowEntity =
                                TvShowEntity(airDate, id, original, name, number, season, summary)
                            entityList.add(tvShowEntity)
                        }
                            mainList = entityList
                            initHorizontalList(mainList,1)

                        //adapter = ItemAdapter(subList)
                        mViewPagerAdapter = ViewPagerAdapter(this, mainList,0)
                        viewPager.adapter = mViewPagerAdapter
                      //  binding.recyclerView1.adapter = adapter
                        viewModel.insertData(list)

                    } else {
//                        Snackbar.make(binding.recyclerView1, "Error Occured ", Snackbar.LENGTH_LONG)
//                            .setAction(
//                                "Retry",
//                                View.OnClickListener {
//                                }).show()
                    }
                }
            }
        }

        viewModel.loadListOfData()
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("gg","dg+$position")

            }

            override fun onPageSelected(position: Int) {
                Log.d("gg","dg+$position")
                binding.listItemSeason.text = "Season : ${position+1}"
                initHorizontalList(mainList,position+1)

            }
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("gg","dg+$state")

            }
        })
    }


    private fun listItemClicked(data: SeasonNumbers) {
        binding.listItemSeason.text = "Season : ${data.number}"
           initHorizontalList(mainList,data.number)
        mViewPagerAdapter = ViewPagerAdapter(this, mainList,data.number)
        viewPager.adapter = mViewPagerAdapter
    }


    private fun initHorizontalList(mainLists: List<TvShowEntity>,selected:Int) {

        val list = getSeason(mainLists)
        list.forEach {
            if (it.number==selected){
                it.isSelected=true
            }
        }
        adapterHorizontal = NumberPickerAdapter(list, this,selected) {
            listItemClicked(it)
            season = it.number

            //initViewModel()
        }

         val mid=list.size/2
         if (selected>=mid){
            binding.recyclerViewHorizontal.scrollToPosition(selected-1)
         }


        binding.recyclerViewHorizontal.adapter = adapterHorizontal
    }

    private fun getSeason(mainList: List<TvShowEntity>): List<SeasonNumbers> {

        val list = mainList.map {
            SeasonNumbers(it.season!!,false)
        }
        return list.distinct().toList()
    }

}