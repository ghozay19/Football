package com.ghozy19.footballapps.favorite.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.db.FavoriteMatch
import kotlinx.android.synthetic.main.match_items_fav.view.*

class MatchFavAdapter(private val context: Context, private val fav: List<FavoriteMatch>, private val listener: (FavoriteMatch)  -> Unit)
    : RecyclerView.Adapter<MatchFavAdapter.MatchFavViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MatchFavViewHolder = MatchFavViewHolder(LayoutInflater.from(context).inflate(R.layout.match_items_fav, parent, false))

    override fun getItemCount(): Int = fav.size

    override fun onBindViewHolder(holder: MatchFavViewHolder, position: Int) {
        holder.bindItem(fav[position], listener)

    }

    class MatchFavViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItem(fav: FavoriteMatch, listener: (FavoriteMatch) -> Unit){


            itemView.idEventMatch.text = fav.eventId

            itemView.clubHomeFav.text = fav.homeTeamName
            itemView.clubAwayFav.text = fav.awayTeamName

            if (fav.homeTeamScore == "null") {
                itemView.scoreFav.text = " VS "
            } else {
                itemView.scoreFav.text = fav.homeTeamScore + " : " + fav.awayTeamScore
            }

            itemView.dateMatchFav.text = fav.dateEvent
            itemView.timeMatchFav.text = fav.timeEvent



            itemView.setOnClickListener { listener(fav) }

        }
    }
}