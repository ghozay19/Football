package com.ghozy19.footballapps.view

import com.ghozy19.footballapps.model.Player

interface PlayerView{
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data: List<Player>)
}