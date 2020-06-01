package com.example.gamebacklog.Database.Repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.Database.Interfaces.BodyPartDAO
import com.example.workoutassistant.database.BodyPartRoomDatabase
import com.example.workoutassistant.database.BodyPartRoomDatabase.Companion.getDatabase
import com.example.workoutassistant.model.BodyPart

class BodyPartRepo(context: Context) {
    private val bodyPartDAO : BodyPartDAO


    init {
        val database = BodyPartRoomDatabase.getDatabase(context)

        bodyPartDAO = database!!.bodyPartDAO()
    }

    fun getBodyParts () :  LiveData<List<BodyPart>>{
        return bodyPartDAO.getBodyParts()
    }

    suspend fun insertBodyPart(bodyPart: BodyPart){
         bodyPartDAO.insertBodyPart(bodyPart)
    }






}