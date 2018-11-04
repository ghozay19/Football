package com.ghozy19.footballapps.view.detailClub

import com.ghozy19.footballapps.model.club.Club

interface DetailClubView {
    fun showLoading()
    fun hideLoading()
    fun showDetailClub(data: List<Club>)
}