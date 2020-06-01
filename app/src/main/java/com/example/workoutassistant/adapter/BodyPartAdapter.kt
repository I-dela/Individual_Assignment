package com.example.workoutassistant.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutassistant.R
import com.example.workoutassistant.model.BodyPart
import kotlinx.android.synthetic.main.item_workout.view.*

class BodyPartAdapter(private val bodyparts: List<BodyPart>) : RecyclerView.Adapter<BodyPartAdapter.ViewHolder>(){

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(bodypart: BodyPart){
            itemView.tvBodyPart.text = bodypart.name



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent , false))
    }

    override fun getItemCount(): Int {
        return bodyparts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(bodyparts[position])
    }
}