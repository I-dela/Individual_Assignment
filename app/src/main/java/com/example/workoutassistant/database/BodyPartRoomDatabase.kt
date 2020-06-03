package com.example.workoutassistant.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.Database.Interfaces.BodyPartDAO
import com.example.workoutassistant.model.BodyPart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ BodyPart::class], version = 1, exportSchema = false)
abstract class BodyPartRoomDatabase : RoomDatabase() {

    abstract fun bodyPartDAO(): BodyPartDAO


    companion object{

        private const val DATABASE_NAME = "GAME_DATABASE"


        @Volatile
        private var INSTANCE :BodyPartRoomDatabase? = null

        fun getDatabase(context: Context) : BodyPartRoomDatabase?{
            if (INSTANCE == null){
                synchronized(BodyPartRoomDatabase::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BodyPartRoomDatabase::class.java,
                            DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate( db: SupportSQLiteDatabase) {

                                    super.onCreate(db)


                                    INSTANCE?.let { database ->
                                        CoroutineScope(Dispatchers.IO).launch {

                                            database.bodyPartDAO().insertBodyPart(BodyPart("Chest", true
                                            ))
                                            database.bodyPartDAO().insertBodyPart(BodyPart("Shoulders", false
                                            ))
                                            database.bodyPartDAO().insertBodyPart(BodyPart("Back", false
                                            ))
                                            database.bodyPartDAO().insertBodyPart(BodyPart("Biceps", false
                                            ))
                                            database.bodyPartDAO().insertBodyPart(BodyPart("Legs", false
                                            ))
                                            database.bodyPartDAO().insertBodyPart(BodyPart("Triceps", false
                                            ))




                                        }
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