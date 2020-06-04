package com.example.workoutassistant.adapter

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutassistant.R
import com.example.workoutassistant.model.BodyPart
import com.example.workoutassistant.model.Level
import kotlinx.android.synthetic.main.item_level.view.*
import kotlinx.android.synthetic.main.item_workout.view.*

class DataAdapter(
    private val data: List<Any> = emptyList(),
    private val onClick: (Any) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class BodyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(bodypart: BodyPart) {
            itemView.tvBodyPart.text = bodypart.name

            bodypart.imageName?.let { itemView.clImage.setBackgroundResource(it) }


        }

        init {
            itemView.setOnClickListener { onClick(data[adapterPosition]) }
        }

    }

    inner class LevelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(level: Level) {
            itemView.tvLevel.text = level.name
            itemView.clImage2.setBackgroundColor(level.difficulty)

        }

        init {
            itemView.setOnClickListener { onClick(data[adapterPosition]) }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BODY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_workout, parent, false)
                BodyViewHolder(view)
            }
            TYPE_LEVELS -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_level, parent, false)
                LevelsViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = data[position]
        when (holder) {
            is BodyViewHolder ->{
                holder.bind(element as BodyPart)



            }
            is LevelsViewHolder -> {
                holder.bind(element as Level)

            }
            else -> throw IllegalArgumentException()
        }


    }


    override fun getItemViewType(position: Int): Int {
        return when (data[position]) {
            is BodyPart -> TYPE_BODY
            else -> TYPE_LEVELS
        }
    }

    companion object {
        private const val TYPE_BODY = 0
        private const val TYPE_LEVELS = 1
        private const val TYPE_WORKOUTS = 2
    }
}