package com.example.workoutassistant.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
class Level(

    var name : String,

    var difficulty: Int,


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")

    var id: Long? = null


): Parcelable {

}