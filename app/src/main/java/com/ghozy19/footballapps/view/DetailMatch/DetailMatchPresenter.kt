package com.ghozy19.footballapps.view.DetailMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.ghozy19.footballapps.model.team.ResponseClub
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
        private val view: DetailMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getDetailClub(home: String?, away: String?){
        view.showLoading()
        doAsync {
            val dataHome = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailClub(home)),
                    ResponseClub::class.java
            )
            val dataAway = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailClub(away)),
                    ResponseClub::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailClub(dataHome.teams, dataAway.teams)
            }

        }
    }

    fun getDetailMatch(league: String?){
        view.showLoading()
        doAsync {

            val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getMatchDetail(league)),
                            ResponseEvent::class.java)

            uiThread {
                view.hideLoading()
                view.showDetailMatch(data.events)
            }

        }
    }



}