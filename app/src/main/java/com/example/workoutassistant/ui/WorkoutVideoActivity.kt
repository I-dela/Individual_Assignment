package com.example.workoutassistant.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.workoutassistant.R
import kotlinx.android.synthetic.main.activity_workout_video.*

class WorkoutVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_video)


        initViews()

    }



    fun initViews(){
        Toast.makeText(this,intent.getStringExtra("BodyPart"), Toast.LENGTH_SHORT).show()


        tvTitleWorkoutVideo.text = intent.getStringExtra("BodyPart")

        tvDescriptionWorkoutVideo.text = intent.getStringExtra("Level")


        workoutImage.setBackgroundResource(intent.getIntExtra("BodyPartImage", 0))

    }
}
