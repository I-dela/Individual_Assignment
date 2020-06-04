package com.example.workoutassistant.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workoutassistant.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view: View = inflater.inflate(R.layout.fragment_home, container, false)







        view.quick.setOnClickListener {
            val intent = Intent(activity, WorkoutVideoActivity::class.java)
            intent.putExtra("BodyPart" , "Chest")
            intent.putExtra("Level" , "Advanced")
            intent.putExtra("BodyPartImage" ,R.drawable.chest)
            intent.putExtra("levelImage" , Color.parseColor("#CC0000"))
            startActivity(intent)
        }


        // Return the fragment view/layout
        return view
    }


}