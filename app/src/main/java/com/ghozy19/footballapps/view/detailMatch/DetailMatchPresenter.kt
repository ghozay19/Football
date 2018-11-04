package com.ghozy19.footballapps.view.detailMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.club.ResponseClub
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter(
        private val view: DetailMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson) {

    fun getDetailClub(home: String?, away: String?) {
        view.showLoading()
        async(UI) {
            val dataHome = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getDetailClub(home)),
                        ResponseClub::class.java
                )
            }
            val dataAway = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getDetailClub(away)),
                        ResponseClub::class.java
                )
            }
            view.hideLoading()
            view.showDetailClub(dataHome.await().teams, dataAway.await().teams)
        }

    }


    fun getDetailMatch(league: String?) {
        view.showLoading()
        async(UI) {

            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getMatchDetail(league)),
                        ResponseEvent::class.java)

            }
            view.hideLoading()
            view.showDetailMatch(data.await().events)
        }

    }
}

