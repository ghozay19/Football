package com.ghozy19.footballapps.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.model.team.Club
import kotlinx.android.synthetic.main.fragment_club_desc.*


class ClubDescFragment : Fragment() {

    private lateinit var club: Club

    companion object {
        fun newInstance(teamId: Club): ClubDescFragment {
            val fragment = ClubDescFragment()
            fragment.club = teamId

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_club_desc, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        descClub.text = club.teamDescription


    }


}
