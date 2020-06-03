package com.example.workoutassistant.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties

data class WorkoutVideo(



    var markedAsDone : Boolean = false,

    var name : String,

    var VideoUrl : String,

    var id : String

) : Parcelable {
}