package com.example.workoutassistant.ui.viemodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.gamebacklog.Database.Repositories.BodyPartRepo
import com.example.workoutassistant.model.BodyPart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BodyPartsViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val bodyPartRepo = BodyPartRepo(application.applicationContext)


    val bodyParts :LiveData<List<BodyPart>> = bodyPartRepo.getBodyParts()

    val favourites :LiveData<List<BodyPart>> = bodyPartRepo.getBodyPartsFavourite()
}