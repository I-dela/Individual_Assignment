package com.example.workoutassistant.database

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.Database.Interfaces.BodyPartDAO
import com.example.workoutassistant.R
import com.example.workoutassistant.model.BodyPart
import com.example.workoutassistant.model.WorkoutVideo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [BodyPart::class], version = 1, exportSchema = false)
abstract class BodyPartRoomDatabase : RoomDatabase() {

    abstract fun bodyPartDAO(): BodyPartDAO



    companion object {

        private const val DATABASE_NAME = "GAME_DATABASE"


        @Volatile
        private var INSTANCE: BodyPartRoomDatabase? = null

        fun getDatabase(context: Context): BodyPartRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BodyPartRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BodyPartRoomDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {

                                    super.onCreate(db)


                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {

                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Chest", true, R.drawable.chest
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Shoulders", false, R.drawable.shoulders
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Back", false, R.drawable.back
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Biceps", false, R.drawable.biceps
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Legs", false, R.drawable.legs
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Triceps", false, R.drawable.triceps
                                                )
                                            )
                                            database.bodyPartDAO().insertBodyPart(
                                                BodyPart(
                                                    "Abs", false, R.drawable.abs
                                                )
                                            )


                                        }
                                    }

                                    val database = FirebaseFirestore.getInstance()

                                    val workoutsRef = database.collection("Workouts")

                                    val workout = WorkoutVideo("Ja", R.raw.back, "Back", "Beginner")
                                    val workout2 = WorkoutVideo("Ja", R.raw.backadv, "Back", "Advanced")
                                    val workout3 = WorkoutVideo("Ja", R.raw.chestadv, "Chest", "Advanced")






                                    workoutsRef.add(workout).addOnSuccessListener {
                                        Log.w("success:", "success adding document")


                                    }.addOnFailureListener { e ->
                                        Log.w("Error:", "Error adding document", e)
                                    }



                                }

                            })
                            .build()
                    }
                }
            }

            return INSTANCE
        }

    }

}