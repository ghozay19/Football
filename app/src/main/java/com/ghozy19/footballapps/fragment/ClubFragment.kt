package com.ghozy19.footballapps.fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.ghozy19.footballapps.activity.DetailClubsActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.adapter.ClubAdapter
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.club.Club
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.club.ClubPresenter
import com.ghozy19.footballapps.view.club.ClubView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_club.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh


class ClubFragment : Fragment(), ClubView {


    private lateinit var adapter: ClubAdapter
    private lateinit var presenter: ClubPresenter
    private lateinit var leagueName: String
    private var club: MutableList<Club> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_club, container, false)
        setHasOptionsMenu(true)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val request = ApiRepository()
        val gson = Gson()
        presenter = ClubPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.id_league)
        listspinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        listspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val getLeague = listspinner.selectedItemPosition
                leagueName = spinnerItemsId[getLeague].toString()
                presenter.getClubList(leagueName)
            }

        }

        adapter = ClubAdapter(ctx, club) {
            ctx.startActivity<DetailClubsActivity>(
                    "club" to it)
        }

        rvViewMatch.layoutManager = LinearLayoutManager(context)
        rvViewMatch.adapter = adapter



        swipeRefresh.onRefresh {
            presenter.getClubList(leagueName)
        }
        swipeRefresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)


    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_menu, menu)

        val menuItem = menu?.findItem(R.id.menuView)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getSearchClub(query)
                return false
            }

        })

    }


    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showClub(data: List<Club>) {
        Log.i("On Show Match : ", "Data Size : ${data.size}")
        swipeRefresh.isRefreshing = false
        club.clear()
        club.addAll(data)
        adapter.notifyDataSetChanged()

    }

}
