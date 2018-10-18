package com.ghozy19.footballapps

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.DetailClub.DetailClubPresenter
import com.ghozy19.footballapps.view.DetailClub.DetailClubView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_club.*

class DetailClubActivity : AppCompatActivity(), DetailClubView {


    private lateinit var presenter: DetailClubPresenter
    private lateinit var progressBar: ProgressBar

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_club)

        progressBar = progressBarDc

        val intent = intent
        id = intent.getStringExtra("id")
        Log.d("dapet idnya ga ?",id)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailClubPresenter(this,request,gson)
        presenter.getDetailClub(id)



    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailClub(data: List<Club>) {
        Glide.with(this)
                .load(data.get(index = 0).teamBadge)
                .into(clubLogoDetail)

        clubStadiumDetail.text = data.get(index = 0).teamStadium + " stadium"
        clubYearsDetail.text = data.get(index = 0).teamFormedYear
        clubDescDetail.text = data.get(index = 0).teamDescription
        clubNameDetail.text = data.get(index = 0).teamName

    }



}
