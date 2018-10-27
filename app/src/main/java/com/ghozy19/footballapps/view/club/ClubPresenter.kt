package com.ghozy19.footballapps.view.club

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.team.ResponseClub
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ClubPresenter(
        private val view: ClubView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getClubList(league: String?) {
        view.showLoading()

        async(UI){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getClub(league)),
                        ResponseClub::class.java
                )
            }
            view.showClub(data.await().teams)
            view.hideLoading()
        }
    }





}