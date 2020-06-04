package com.example.workoutassistant.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutassistant.R
import com.example.workoutassistant.adapter.DataAdapter
import com.example.workoutassistant.model.BodyPart
import com.example.workoutassistant.model.Level
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_workout_video.*
import kotlinx.android.synthetic.main.content_choose_level.*
import kotlinx.android.synthetic.main.fragment_workout.*

class ChooseLevelActivity : AppCompatActivity() {


    private val levels  = arrayListOf<Level>()
    private val adapter  = DataAdapter(levels) { level -> onColorClick(level as Level) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level)


        loadData()
        initViews()





    }


    private fun loadData(){

        levels.add(Level("Beginner", Color.parseColor("#7CFC00")))
        levels.add(Level("Intermediate", Color.parseColor("#e67e00")))
        levels.add(Level("Advanced", Color.parseColor("#CC0000")))

    }


    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvLevels.layoutManager =
            LinearLayoutManager(this@ChooseLevelActivity, RecyclerView.VERTICAL, false)
        rvLevels.adapter = adapter
        rvLevels.addItemDecoration(
            DividerItemDecoration(
                this@ChooseLevelActivity,
                DividerItemDecoration.VERTICAL
            )
        )

    }




    private fun onColorClick(level: Level) {
        val intentToWorkoutVideoActivity = Intent(this, WorkoutVideoActivity::class.java)
        intentToWorkoutVideoActivity.putExtra("BodyPart" , intent.getStringExtra("BodyPart"))
        intentToWorkoutVideoActivity.putExtra("Level" , level.name)
        intentToWorkoutVideoActivity.putExtra("BodyPartImage" , intent.getStringExtra("image"))
        startActivity(intentToWorkoutVideoActivity)
    }




}
