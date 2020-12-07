package com.ook.academy.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ook.academy.project.pojo.ActorData

class ActorAdapter() : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(MockData.actors[position])

    }

    override fun getItemCount(): Int = MockData.movies.size

}

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val coverImageView: ImageView = itemView.findViewById(R.id.actor_image)
    private val nameTextView: TextView = itemView.findViewById(R.id.actor_name)

    fun bind(data: ActorData) {
        coverImageView.setImageResource(data.coverId)
        nameTextView.setText(data.nameId)
    }
}

