package com.example.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movies.ui.PopularFragment
import com.example.movies.ui.RecyclerFragment
import com.example.movies.ui.UpcomingFragment


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var list: ArrayList<Fragment> = ArrayList()

    init {
        list.add(PopularFragment())
        list.add(RecyclerFragment())
        list.add(UpcomingFragment())
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            0 -> "Popular Movies"
            1 -> "Top Rated Movies"
            2 -> "Upcoming Movies"
            else -> "over"
        }
    }
}
