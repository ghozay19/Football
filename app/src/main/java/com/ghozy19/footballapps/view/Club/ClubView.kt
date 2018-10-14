package com.ghozy19.footballapps.view.Club

import com.ghozy19.footballapps.model.team.Club

//TODO RAPIHIN

interface ClubView {
    fun showLoading()
    fun hideLoading()
    fun showClub(data: List<Club> )
}