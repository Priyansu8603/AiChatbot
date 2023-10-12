package com.example.aichatbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.aichatbot.databinding.ActivityWalkingThroughOnBoardingBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class walkingThroughOnBoarding : AppCompatActivity() {

    private val onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            when(position){
                0->{
                    skipBtn.text = "Skip"
                    skipBtn.visible()
                    nextBtn.visible()
                    previousBtn.gone()
                }

                pagerList.size-1->{
                    skipBtn.text= "Get Started"
                    nextBtn.gone()
                    previousBtn.visible()
                    skipBtn.visible()
                }
                else->{
                    skipBtn.text = "Skip"
                    skipBtn.visible()
                    nextBtn.visible()
                    previousBtn.visible()
                }
            }
        }
    }


    private val pagerList = arrayListOf(
        Page("Title",R.raw.splashanim,"This is Description Box"),
        Page("Title",R.raw.splashanim1,"This is Description Box"),
        Page("Title",R.raw.splashanim1,"This is Description Box"),
        Page("Title",R.raw.splashanim2,"This is Description Box")
    )



    lateinit var onBoardingViewPager2 :ViewPager2
    lateinit var skipBtn : Button
    lateinit var previousBtn : Button
    lateinit var nextBtn : Button

    private lateinit var binding: ActivityWalkingThroughOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalkingThroughOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBoardingViewPager2 = findViewById(R.id.slideViewPager)
        skipBtn = findViewById(R.id.skipBtn)
        nextBtn = findViewById(R.id.nextBtn)
        previousBtn = findViewById(R.id.previousBtn)

        onBoardingViewPager2.apply {
            adapter = OnBoardingAdapter(this@walkingThroughOnBoarding,pagerList)
            registerOnPageChangeCallback(onBoardingPageChangeCallback)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout,onBoardingViewPager2){tab,position -> }.attach()

        nextBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem < onBoardingViewPager2.adapter!!.itemCount-1)
                onBoardingViewPager2.currentItem += 1
            else{
                homeScreenIntent()
            }
        }

        previousBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem>0){
                onBoardingViewPager2.currentItem -= 1
            }
        }

        skipBtn.setOnClickListener {
            homeScreenIntent()
        }
    }

    override fun onDestroy() {
        onBoardingViewPager2.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    private fun homeScreenIntent() {

        val sharedPreferenceManager = SharedPreferenceManager(this)
        sharedPreferenceManager.isFirstTime = false
        startActivity(Intent(this,HomeScreen::class.java))
        finish()
    }
}