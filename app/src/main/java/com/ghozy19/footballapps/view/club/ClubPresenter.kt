package com.ghozy19.footballapps.view.club

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.team.ResponseClub
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ClubPresenter(
        private val view: ClubView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getClubList(league: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getClub(league)),
                    ResponseClub::class.java
                    )
            uiThread {
                view.hideLoading()
                view.showClub(data.teams)
            }
        }


    }

}