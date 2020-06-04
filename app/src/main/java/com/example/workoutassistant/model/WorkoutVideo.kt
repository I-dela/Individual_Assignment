package com.example.workoutassistant.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize



@IgnoreExtraProperties
 data class WorkoutVideo(




    var name : String? = "",


    var video_id : Int? =null,



    @PrimaryKey
    var id : String? =""



)  {
}