package com.ghozy19.footballapps.view.club

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.club.ResponseClub
import com.ghozy19.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class ClubPresenter(
        private val view: ClubView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getClubList(league: String?) {
        view.showLoading()

        async(context.main){
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

    fun getSearchClub(query: String?, type: Int =1){
        view.showLoading()

        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchClub(query)),
                        ResponseClub::class.java)
            }
            view.showClub(data.await().teams)
            view.hideLoading()
        }


    }





}