package com.presldn.empowerment.view.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.FragmentQuotesBinding
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.networking.dagger.injection.ViewModelFactory
import com.presldn.empowerment.view.adapters.QuotePagerAdapter
import com.presldn.empowerment.view.transformers.ZoomOutPageTransformer
import com.presldn.empowerment.viewmodels.QuoteListViewModel

private const val ARG_QUOTES = "quotes"

/**
 * A simple [Fragment] subclass.
 * Use the [QuotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuotesFragment : Fragment() {
    private val TAG = "QuotesFragment"

    private var quotes: List<Quote>? = null

    private lateinit var viewModel: QuoteListViewModel

    private lateinit var binding: FragmentQuotesBinding

    private lateinit var quotePagerAdapter: QuotePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quotes = it.getParcelableArrayList(ARG_QUOTES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quotes, container, false)

        quotePagerAdapter = QuotePagerAdapter(childFragmentManager)

        binding.viewPager.adapter = quotePagerAdapter
        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, ViewModelFactory(activity!!)).get(QuoteListViewModel::class.java)
        viewModel.quotes.observe(this, Observer { quotes ->
            if (quotes != null) quotePagerAdapter.updateQuoteList(quotes)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: ArrayList<out Quote>) =
            QuotesFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_QUOTES, param1)
                }
            }
    }
}
