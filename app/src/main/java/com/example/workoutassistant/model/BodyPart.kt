package com.example.workoutassistant.model

import android.graphics.drawable.Drawable
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RawQuery
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*


@Parcelize
@Entity
class BodyPart(
    var name: String,


    var isFavourite : Boolean,




    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")

    var id: Long? = null

) : Parcelable {
}