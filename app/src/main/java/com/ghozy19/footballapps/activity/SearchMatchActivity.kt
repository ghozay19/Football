package com.ghozy19.footballapps.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.adapter.MatchSearchAdapter
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.matchevent.EventsItem
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.searchMatch.SearchMatchPresenter
import com.ghozy19.footballapps.view.searchMatch.SearchMatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {


    private var match: MutableList<EventsItem> = mutableListOf()
    private lateinit var view: SearchView
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: MatchSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val request = ApiRepository()
        val gson = Gson()
        presenter = SearchMatchPresenter(this, request, gson)


        adapter = MatchSearchAdapter(ctx, match) {
            startActivity<DetailMatchActivity>(
                    "idEvent" to it

            )
        }
        recyclerView.layoutManager = LinearLayoutManager(ctx)
        recyclerView.adapter = adapter

        presenter.getEventListSearch()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val menuItem = menu?.findItem(R.id.menuView)
        view = menuItem?.actionView as SearchView


        view.isIconified = false

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.getEventListSearch(query.toString())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.getEventListSearch(query.toString())


                return false
            }
        })

        return true

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventsList(data: List<EventsItem>) {
        hideLoading()
        match.clear()

        data.forEach {
            if (it.strSport.equals("Soccer")) {
                match.add(it)
            }
        }
        adapter.notifyDataSetChanged()

        Log.d("data", "kemana datanya" + data)
    }

}
