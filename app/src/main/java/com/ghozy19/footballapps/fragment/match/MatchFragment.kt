package com.ghozy19.footballapps.fragment.match


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
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : Fragment() {

    private lateinit var mViewPager: ViewPager
    private lateinit var mTablayout: TabLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_match, container, false)

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

        internal var nextMatch = resources.getString(R.string.next_match)
        internal var lastMatch = resources.getString(R.string.last_match)
        internal val tabs = arrayOf(nextMatch, lastMatch)

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return NextMatchFragment()
                1 -> return LastMatchFragment()
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
