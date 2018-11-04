package com.ghozy19.footballapps.fragment.match


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.ghozy19.footballapps.*
import com.ghozy19.footballapps.adapter.MatchAdapter
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.nextMatch.NextMatchPresenter
import com.ghozy19.footballapps.view.nextMatch.NextMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast


class NextMatchFragment : Fragment(), NextMatchView {


    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var leagueName: String
    private var match: MutableList<EventsItem> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_next_match, container, false)

        spinner = view.listSpinner
        swipeRefreshLayout = view.swipeRefresh
        progressBar = view.progressBar



        val request = ApiRepository()
        val gson = Gson()
        presenter = NextMatchPresenter(this, request, gson)


        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerItemsId = resources.getStringArray(R.array.id_league)
        spinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val getLeague = spinner.selectedItemPosition
                leagueName = spinnerItemsId[getLeague].toString()
                presenter.getNextMatch(leagueName)

            }

        }


        adapter = MatchAdapter(ctx, match) {
            ctx.startActivity<DetailMatchActivity>(
                    "idEvent" to  it
            )
        }

        listMatch = view.findViewById(R.id.rvViewMatch)
        listMatch.layoutManager = LinearLayoutManager(context)
        listMatch.adapter = adapter

        swipeRefreshLayout.onRefresh {
            presenter.getNextMatch(leagueName)
        }
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW)


        return view

    }


    override fun showLaoding() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showNextMatch(data: List<EventsItem>?) {
        swipeRefreshLayout.isRefreshing = false
        match.clear()
        if (data != null) {
            match.addAll(data)
        }
        adapter.notifyDataSetChanged()
    }

    override fun showNull() {

        toast("There is no upcoming match(es)")
    }



}