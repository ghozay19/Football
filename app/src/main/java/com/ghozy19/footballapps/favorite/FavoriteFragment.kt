package com.ghozy19.footballapps.favorite


import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghozy19.footballapps.R
import com.ghozy19.footballapps.favorite.club.FavoriteClubFragment
import com.ghozy19.footballapps.favorite.match.FavoriteMatchFragment

class FavoriteFragment : Fragment() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mTablayout: TabLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)


        mViewPager = view.findViewById(R.id.viewpager_main) as ViewPager
        mViewPager.adapter = sliderAdapter(childFragmentManager)

        mTablayout = view.findViewById(R.id.tabs_main) as TabLayout
        mTablayout.setupWithViewPager(mViewPager)

        mTablayout.post { mTablayout.setupWithViewPager(mViewPager) }

        mTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return view
    }

    private inner class sliderAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        internal var club = resources.getString(R.string.club)
        internal var match = resources.getString(R.string.match_fragment)
        internal val tabs = arrayOf(club, match)

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return FavoriteClubFragment()
                1 -> return FavoriteMatchFragment()
            }
            return null
        }

        override fun getCount(): Int {
            return tabs.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return tabs[position]
        }
    }

}