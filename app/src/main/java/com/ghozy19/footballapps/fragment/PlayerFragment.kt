package com.ghozy19.footballapps.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.ghozy19.footballapps.activity.DetailPlayerActivity
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.adapter.PlayerAdapter
import com.ghozy19.footballapps.api.ApiRepository
import com.ghozy19.footballapps.model.Player
import com.ghozy19.footballapps.utils.invisible
import com.ghozy19.footballapps.utils.visible
import com.ghozy19.footballapps.view.player.PlayerPresenter
import com.ghozy19.footballapps.view.player.PlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx


class PlayerFragment : Fragment(), PlayerView {

    private lateinit var listClub: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: PlayerAdapter
    private lateinit var presenter: PlayerPresenter
    private var player: MutableList<Player> = mutableListOf()



    private lateinit var teamId: String

    companion object {
        fun newInstance(teamId: String): PlayerFragment {
            val fragment = PlayerFragment()
            fragment.teamId = teamId

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        progressBar = view.progressBarPlayer
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(this, request, gson)
        presenter.getPlayerList(teamId)


        adapter = PlayerAdapter(ctx, player) {
            ctx.startActivity<DetailPlayerActivity>("Player" to it)
        }


        listClub = view.rvViewPlayer
        listClub.layoutManager = LinearLayoutManager(context)
        listClub.adapter = adapter

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayer(data: List<Player>) {
        Log.i("On Show Match : ", "Data Size : ${data.size}")
//    swipeRefreshLayout.isRefreshing = false
        player.clear()
        player.addAll(data)
        adapter.notifyDataSetChanged()


    }
}
