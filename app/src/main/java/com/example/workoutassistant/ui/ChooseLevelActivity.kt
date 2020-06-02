package com.example.workoutassistant.ui

import android.content.Intent
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

        levels.add(Level("Beginner"))
        levels.add(Level("Intermediate"))
        levels.add(Level("Advanced"))

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


        createItemTouchHelper().attachToRecyclerView(rvLevels)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            // onSwiped has been changed to use the ViewModel deleteReminder method
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {





            }
        }
        return ItemTouchHelper(callback)
    }


    private fun onColorClick(level: Level) {
        val intentToWorkoutVideoActivity = Intent(this, WorkoutVideoActivity::class.java)
        intentToWorkoutVideoActivity.putExtra("BodyPart" , intent.getStringExtra("BodyPart"))
        intentToWorkoutVideoActivity.putExtra("Level" , level.name)
        startActivity(intentToWorkoutVideoActivity)
    }




}
