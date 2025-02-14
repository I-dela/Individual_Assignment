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

    fun getBodyPartsFavourite(): LiveData<List<BodyPart>>{
        return bodyPartDAO.getBodyPartsFavourite()
    }

    suspend fun insertBodyPart(bodyPart: BodyPart){
         bodyPartDAO.insertBodyPart(bodyPart)
    }

  suspend  fun setAsFavourite(bodyPartId : Long){
        bodyPartDAO.setAsFavourite(bodyPartId)
    }

    suspend fun unSetAsFavourite(bodyPartId: Long){
        bodyPartDAO.unSetAsFavourite(bodyPartId)
    }






}