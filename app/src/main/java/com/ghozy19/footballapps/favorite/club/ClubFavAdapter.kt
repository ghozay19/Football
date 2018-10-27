package com.ghozy19.footballapps.favorite.club

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.db.FavoriteClub
import kotlinx.android.synthetic.main.club_item.view.*

class ClubFavAdapter(private val context: Context, private val fav: List<FavoriteClub>, private val listener: (FavoriteClub)  -> Unit)
    :RecyclerView.Adapter<ClubFavAdapter.ClubFavViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ClubFavViewHolder = ClubFavViewHolder(LayoutInflater.from(context).inflate(R.layout.club_item, parent, false))

    override fun getItemCount(): Int = fav.size

    override fun onBindViewHolder(holder: ClubFavViewHolder, position: Int) {
        holder.bindItem(fav[position], listener)

    }

    class ClubFavViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItem(fav: FavoriteClub, listener: (FavoriteClub) -> Unit){

            itemView.tvClubName.text = fav.teamName
            Glide.with(itemView.context)
                    .load(fav.teamBadge)
                    .into(itemView.ivClubLogo)

            itemView.setOnClickListener { listener(fav) }

        }
    }
}