package com.ghozy19.footballapps.view.DetailClub

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.team.ResponseClub
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailClubPresenter(
        private val view: DetailClubView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
) {

    fun getDetailClub(teamId: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getDetailClub(teamId)),
                    ResponseClub::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailClub(data.teams)
            }
        }

    }


}