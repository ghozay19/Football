package com.ghozy19.footballapps

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.model.Player
import kotlinx.android.synthetic.main.activity_detail_player.*
import org.jetbrains.anko.ctx

class DetailPlayerActivity : AppCompatActivity() {
    private lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        supportActionBar?.title = getString(R.string.player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra("Player")

        loadDetail()

    }

    private fun loadDetail() {


        playerName.text = player.playerName
        playerOverview.text = player.playerDescription
        playerPosition.text = player.playerPosition
        playerNationality.text = player.playerNationality

        playerHeight.text = player.playerHeight
        playerWeight.text = player.playerWeight


        Glide.with(ctx).load(player.playerPhoto).into(playerPhoto)

    }
}
