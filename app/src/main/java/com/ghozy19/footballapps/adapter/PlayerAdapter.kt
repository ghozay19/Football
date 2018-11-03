package com.ghozy19.footballapps.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.model.Player
import kotlinx.android.synthetic.main.player_list.view.*

class PlayerAdapter(private val context: Context, private val player: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PlayerViewHolder = PlayerAdapter.PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.player_list, parent, false))

    override fun getItemCount() = player.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(player[position],listener)
    }


    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(player: Player, listener: (Player) -> Unit){

            itemView.tvPlayerName.text = player.playerName
            itemView.tvPlayerPosition.text = player.playerPosition

            Glide.with(itemView.context)
                    .load(player.playerPhoto)
                    .into(itemView.ivPlayerPicture)

            itemView.setOnClickListener { listener(player) }

        }
    }


}