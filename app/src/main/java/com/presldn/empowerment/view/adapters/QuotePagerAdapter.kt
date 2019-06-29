package com.presldn.empowerment.view.adapters

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.view.fragments.QuoteSlidePagerFragment

class QuotePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private lateinit var quoteList: List<Quote>

    override fun getItem(position: Int): Fragment = QuoteSlidePagerFragment.newInstance(quoteList[position])

    override fun getCount(): Int = if (::quoteList.isInitialized) quoteList.size else 0

    fun updateQuoteList(quoteList: List<Quote>) {
        this.quoteList = quoteList
        notifyDataSetChanged()
    }

}