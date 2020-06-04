package com.example.workoutassistant.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.workoutassistant.R
import com.example.workoutassistant.model.WorkoutVideo
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController


    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference

    private var workoutsRef: DatabaseReference = database.child("Workouts")




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navControllerSetup()




        firebase.setOnClickListener {
            saveVideos()
        }

    }

    private fun saveVideos(){

        val workout = WorkoutVideo("Ja", R.raw.back)

        workoutsRef.setValue(workout).addOnSuccessListener {
            Toast.makeText(this,"Succes", Toast.LENGTH_LONG).show()
        }









    }





    private fun navControllerSetup() {
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )


        bottomNav.setupWithNavController(navController)


        val appBarConfiguration = AppBarConfiguration
            .Builder(
                R.id.iHome,
                R.id.iWorkout
            )
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {

            else -> super.onOptionsItemSelected(item)
        }


    }


}
