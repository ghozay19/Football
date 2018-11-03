package com.ghozy19.footballapps.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.ghozy19.footballapps.DetailClubActivity
import com.ghozy19.footballapps.DetailClubsActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.adapter.ClubAdapter
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.team.Club
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.club.ClubPresenter
import com.ghozy19.footballapps.view.club.ClubView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_club.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


class ClubFragment : Fragment(), ClubView {


    private lateinit var listClub: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var adapter: ClubAdapter
    private lateinit var presenter: ClubPresenter
    private lateinit var leagueName: String
    private var club: MutableList<Club> = mutableListOf()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_club, container, false)

        spinner = view.listspinner
        swipeRefreshLayout = view.swipeRefresh
        progressBar = view.progressBar

        val request = ApiRepository()
        val gson = Gson()
        presenter = ClubPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.id_league)
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val getLeague = spinner.selectedItemPosition
                leagueName = spinnerItemsId[getLeague].toString()
                presenter.getClubList(leagueName)
            }

        }


//        adapter = ClubAdapter(ctx, club) {
//            ctx.startActivity<DetailClubActivity>(
//            "id" to it.teamId)
//        }

        adapter = ClubAdapter(ctx, club) {
            ctx.startActivity<DetailClubsActivity>(
                    "club" to it)
        }


        listClub = view.findViewById(R.id.rvViewMatch)
        listClub.layoutManager = LinearLayoutManager(context)
        listClub.adapter = adapter



        swipeRefreshLayout.onRefresh {
            presenter.getClubList(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)



        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showClub(data: List<Club>) {
        Log.i("On Show Match : ", "Data Size : ${data?.size}")
        swipeRefreshLayout.isRefreshing = false
        club.clear()
        club.addAll(data)
        adapter.notifyDataSetChanged()

    }


}
