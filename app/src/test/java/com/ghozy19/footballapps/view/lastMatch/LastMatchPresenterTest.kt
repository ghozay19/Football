package com.ghozy19.footballapps.view.lastMatch

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.model.matchevent.ResponseEvent
import com.ghozy19.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LastMatchPresenterTest {


    @Mock
    private lateinit var view: LastMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: LastMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LastMatchPresenter(view,apiRepository,gson, TestContextProvider())
    }

    @Test
    fun getLastMatch() {
        val clubs: MutableList<EventsItem>  = mutableListOf()
        val response = ResponseEvent(clubs)
        val league = "English Premiere League"


        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getClub(league)), ResponseEvent::class.java)).thenReturn(response)

        presenter.getLastMatch(league)

        Mockito.verify(view).showLoading()
        Mockito.verify(view).showLastMatch(clubs)
        Mockito.verify(view).hideLoading()


    }
}