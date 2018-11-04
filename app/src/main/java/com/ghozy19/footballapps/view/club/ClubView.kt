package com.ghozy19.footballapps.view.club

import com.ghozy19.footballapps.model.club.Club

interface ClubView {
    fun showLoading()
    fun hideLoading()
    fun showClub(data: List<Club> )
}