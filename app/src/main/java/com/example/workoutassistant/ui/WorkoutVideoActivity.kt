package com.example.workoutassistant.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import com.example.workoutassistant.R
import com.example.workoutassistant.model.WorkoutVideo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_workout_video.*
import java.lang.Exception

class WorkoutVideoActivity : AppCompatActivity() {
    var position = 0
    var mController : MediaController?=null

    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    private var workoutsRef: DatabaseReference = database.child("Workouts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_video)





        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(WorkoutVideo::class.java)
                Toast.makeText(this@WorkoutVideoActivity, user?.video_id.toString(), Toast.LENGTH_LONG).show()


                if(mController == null){
                    mController =MediaController(this@WorkoutVideoActivity)
                }

                try {
                    vvWorkout.setMediaController(mController)
                    vvWorkout.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + user?.video_id))
                }catch (e:Exception){
                    Log.e("Error" , e.message)
                }

                vvWorkout.requestFocus()
                vvWorkout.setOnCompletionListener {
                    vvWorkout.seekTo(position)
                    if(position == 0){
                        vvWorkout.start()
                    }else{
                        vvWorkout.pause()
                    }
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Error", databaseError.message)
            }
        }
        workoutsRef.addListenerForSingleValueEvent(valueEventListener)



        initViews()

    }


    fun initVideoView(){


    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("position", vvWorkout.currentPosition)
        vvWorkout.pause()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        position= savedInstanceState.getInt("position")
        vvWorkout.seekTo(position)
    }





    fun initViews(){
        Toast.makeText(this,intent.getStringExtra("BodyPart"), Toast.LENGTH_SHORT).show()


        tvTitleWorkoutVideo.text = intent.getStringExtra("BodyPart")

        tvDescriptionWorkoutVideo.text = intent.getStringExtra("Level")


        workoutImage.setBackgroundResource(intent.getIntExtra("BodyPartImage", 0))

    }
}
