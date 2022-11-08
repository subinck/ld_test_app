package com.subin.ldtestapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.subin.ldtestapplication.R
import com.subin.ldtestapplication.data.database.TvShowEntity
import com.subin.ldtestapplication.data.models.SeasonNumbers
import com.subin.ldtestapplication.data.models.TVShowResponseItem
import com.subin.ldtestapplication.databinding.ActivityMainBinding
import com.subin.ldtestapplication.ui.adapter.ItemAdapter
import com.subin.ldtestapplication.ui.adapter.NumberPickerAdapter
import com.subin.ldtestapplication.ui.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ItemAdapter
    private lateinit var adapterHorizontal: NumberPickerAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private  var mainList:List<TvShowEntity> = arrayListOf()
    private var season:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        initViews()
        initViewModel()
    }

private fun initViews(){
    binding.listItemSeason.text="Season : 1"
        binding.recyclerView1.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHorizontal.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
    var list= listOf<SeasonNumbers>(
        SeasonNumbers(1),
        SeasonNumbers(2),
        SeasonNumbers(3),
        SeasonNumbers(4),
        SeasonNumbers(5),
        SeasonNumbers(6),
        SeasonNumbers(7),
        SeasonNumbers(8),
    )

    adapterHorizontal = NumberPickerAdapter(list,this){
        listItemClicked(it)
        season=it.number
        initViewModel()
    }
    binding.recyclerViewHorizontal.adapter = adapterHorizontal

}
  private  fun initViewModel(){
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
      viewModel.getFromDB().observe(this){dbdata->

          if(dbdata.isNotEmpty()){
              val subList=getSubList(season,dbdata)
              mainList=dbdata
              adapter = ItemAdapter(subList)
              binding.recyclerView1.adapter = adapter

          }else{
              viewModel.getLiveDataObserver().observe(this) { response ->
                  if (response != null) {
                      val list: ArrayList<TVShowResponseItem> = response
                      val entityList:ArrayList<TvShowEntity> = arrayListOf()
                      for (i in list){
                          val airDate = i.airdate
                          val id = i.id
                          val original = i.image?.original
                          val name = i.name
                          val number = i.number
                          val season = i.season
                          val summary = i.summary
                          val tvShowEntity= TvShowEntity(airDate,id,original,name,number,season,summary)
                          entityList.add(tvShowEntity)
                      }
                      mainList=entityList
                      val subList=getSubList(season,entityList)
                      adapter = ItemAdapter(subList)
                      binding.recyclerView1.adapter = adapter
                      viewModel.insertData(list)

                  } else {
                      Snackbar.make(binding.recyclerView1, "Error Occured ", Snackbar.LENGTH_LONG)
                          .setAction(
                              "Retry",
                              View.OnClickListener {
                              }).show()
                  }
              }
          }

      }

      viewModel.loadListOfData()
    }



    private fun listItemClicked(data:SeasonNumbers) {
        binding.listItemSeason.text= "Season : ${data.number}"

    }

   private fun getSubList( season:Int,list:List<TvShowEntity>):List<TvShowEntity>{

        val subList= arrayListOf<TvShowEntity>()
        list.forEach {
            if (it.season==season){
                subList.add(it)
            }
        }
        return subList
    }

}