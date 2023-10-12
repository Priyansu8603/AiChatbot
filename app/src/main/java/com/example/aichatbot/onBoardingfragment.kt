package com.example.aichatbot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView


class onBoardingfragment(val page: Page) : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding,container,false)
        view.setBackgroundResource(R.drawable.activity_background)
        val titleTxt = view.findViewById<TextView>(R.id.titleTxt)
        val DescTxt = view.findViewById<TextView>(R.id.DesTxt)
        val animView = view.findViewById<LottieAnimationView>(R.id.onBoardingAnim)

        titleTxt.text = page.title
        DescTxt.text = page.desc
        animView.setAnimation(page.anim)
        return view
    }


}