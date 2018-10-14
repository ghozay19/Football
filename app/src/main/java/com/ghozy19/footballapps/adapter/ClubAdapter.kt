package com.ghozy19.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.model.team.Club
import kotlinx.android.synthetic.main.club_item.view.*

class ClubAdapter(private val context: Context, private val club: List<Club>, private val listener: (Club) -> Unit)
    : RecyclerView.Adapter<ClubAdapter.ClubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ClubViewHolder = ClubAdapter.ClubViewHolder(LayoutInflater.from(context).inflate(R.layout.club_item, parent, false))

    override fun getItemCount() = club.size

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bindItem(club[position],listener)
    }


    class ClubViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(club: Club, listener: (Club) -> Unit){

            itemView.tvClubName.text = club.teamName
            Glide.with(itemView.context)
                    .load(club.teamBadge)
                    .into(itemView.ivClubLogo)

            itemView.setOnClickListener { listener(club) }

        }
    }


}