package com.example.workoutassistant.ui

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workoutassistant.R
import com.example.workoutassistant.model.WorkoutVideo
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_workout_video.*

class WorkoutVideoActivity : AppCompatActivity() {
    var position = 0
    var mController: MediaController? = null

    private var database = FirebaseFirestore.getInstance()

    private var workoutsRef = database.collection("Workouts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_video)


        if (mController == null) {
            mController = MediaController(this@WorkoutVideoActivity)
        }






        workoutsRef.whereEqualTo("bodyType", intent.getStringExtra("BodyPart")).get().addOnSuccessListener { documentSnapshot ->
            val workoutVideo  = documentSnapshot.toObjects(WorkoutVideo::class.java)[0]

            if(workoutVideo.level == intent.getStringExtra("Level")) {
                try {
                    vvWorkout.setMediaController(mController)
                    vvWorkout.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.back))
                } catch (e: Exception) {
                    Log.e("Error", e.message)
                }
            }

        }

        vvWorkout.requestFocus()
        vvWorkout.setOnCompletionListener {
            vvWorkout.seekTo(position)
            if (position == 0) {
                vvWorkout.start()
            } else {
                vvWorkout.pause()
            }
        }














        initViews()

    }


    fun initVideoView() {


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("position", vvWorkout.currentPosition)
        vvWorkout.pause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position = savedInstanceState.getInt("position")
        vvWorkout.seekTo(position)
    }


    fun initViews() {
        Toast.makeText(this, intent.getStringExtra("BodyPart"), Toast.LENGTH_SHORT).show()


        tvTitleWorkoutVideo.text = intent.getStringExtra("BodyPart")

        tvDescriptionWorkoutVideo.text = intent.getStringExtra("Level")


        workoutImage.setBackgroundResource(intent.getIntExtra("BodyPartImage", 0))

    }
}
