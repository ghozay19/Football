package com.ghozy19.footballapps.view.club

import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.api.TheSportDBApi
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.model.team.ResponseClub
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ClubPresenterTest {

    @Mock
    private lateinit var view: ClubView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: ClubPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = ClubPresenter(view,apiRepository,gson)
    }

    @Test
    fun getClubList() {
        fun testGetClubList(){
            val clubs: MutableList<Club>  = mutableListOf()
            val response = ResponseClub(clubs)
            val league = "English Premiere League"

            `when`(gson.fromJson(apiRepository.doRequest(
                    TheSportDBApi.getClub(league)),
                    ResponseClub::class.java)).thenReturn(response)

            presenter.getClubList(league)

            verify(view).showLoading()
            verify(view).showClub(clubs)
            verify(view).hideLoading()

        }
    }
}