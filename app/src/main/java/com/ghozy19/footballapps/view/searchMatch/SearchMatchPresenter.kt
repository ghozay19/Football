package com.ghozy19.footballapps.view.searchMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.EventsSearchReponse
import com.ghozy19.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(
        private val view: SearchMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getEventListSearch(query: String ="") {
        view.showLoading()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getSearchEvent(query)),
                        EventsSearchReponse::class.java
                )
            }
            view.showEventsList(data.await().event)
            view.hideLoading()
        }
    }





}