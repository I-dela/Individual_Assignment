package com.example.workoutassistant.model

import android.os.Parcelable
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize


@IgnoreExtraProperties
 class WorkoutVideo(




    var name : String? = "",

    @PrimaryKey
    var id : String? =""



)  {
}