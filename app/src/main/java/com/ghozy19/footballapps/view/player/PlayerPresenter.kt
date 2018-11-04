package com.ghozy19.footballapps.view.player

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.PlayerResponse
import com.ghozy19.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(
        private val view: PlayerView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(league: String?) {
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayer(league)),
                        PlayerResponse::class.java
                )
            }
            view.showPlayer(data.await().player)
            view.hideLoading()
        }
    }





}