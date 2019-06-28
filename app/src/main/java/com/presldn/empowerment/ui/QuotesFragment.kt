package com.presldn.empowerment.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.presldn.empowerment.R
import com.presldn.empowerment.models.Quote

private const val ARG_QUOTES = "quotes"

/**
 * A simple [Fragment] subclass.
 * Use the [QuotesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuotesFragment : Fragment() {
    private var quotes: List<Quote>? = null

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
        return inflater.inflate(R.layout.fragment_quotes, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment QuotesFragment.
         */
        @JvmStatic
        fun newInstance(param1: ArrayList<out Quote>) =
            QuotesFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_QUOTES, param1)
                }
            }
    }
}
