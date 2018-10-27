package com.ghozy19.footballapps.view.detailClub

import com.ghozy19.footballapps.model.team.Club

interface DetailClubView {
    fun showLoading()
    fun hideLoading()
    fun showDetailClub(data: List<Club>)
}