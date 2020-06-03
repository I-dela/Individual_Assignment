package com.example.gamebacklog.Database.Interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workoutassistant.model.BodyPart

@Dao
interface BodyPartDAO {


    @Insert
     suspend fun insertBodyPart(bodyPart: BodyPart)

    @Query("SELECT * FROM BODYPART")
    fun getBodyParts() : LiveData<List<BodyPart>>


    @Query("SELECT * FROM BODYPART WHERE isFavourite = 1")
    fun getBodyPartsFavourite() : LiveData<List<BodyPart>>



    @Query("UPDATE BodyPart SET isFavourite = 1 WHERE id = :bodyPartId")
    suspend fun setAsFavourite(bodyPartId : Long)

    @Query("UPDATE BodyPart SET isFavourite = 0 WHERE id = :bodyPartId")
    suspend fun unSetAsFavourite(bodyPartId : Long)




}