package com.subin.ldtestapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.subin.ldtestapplication.databinding.ActivityGuideBinding
import com.subin.ldtestapplication.ui.MainActivity

class GuideActivity : Activity() {
    private lateinit var binding:ActivityGuideBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        binding=DataBindingUtil.setContentView(this,R.layout.activity_guide)
        binding.guideButton.setOnClickListener {
            val i: Intent = Intent(this@GuideActivity,MainActivity::class.java)
            i.putExtra("IsFromGuide","FromGuide")
            startActivity(i)
            finish()
        }

    }
}