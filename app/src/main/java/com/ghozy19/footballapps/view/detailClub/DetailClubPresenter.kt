package com.ghozy19.footballapps.view.detailClub

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.club.ResponseClub
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailClubPresenter(
        private val view: DetailClubView,
        private val apiRepository: ApiRepository,
        private val gson: Gson
) {

    fun getDetailClub(teamId: String) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getDetailClub(teamId)),
                        ResponseClub::class.java
                )

            }
            view.hideLoading()
            view.showDetailClub(data.await().teams)
        }
    }

}
