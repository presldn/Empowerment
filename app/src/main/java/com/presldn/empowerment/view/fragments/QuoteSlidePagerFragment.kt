package com.presldn.empowerment.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.FragmentQuoteSlidePagerBinding
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.viewmodels.QuoteViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CURRENT_QUOTE = "current_quote"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [QuoteSlidePagerFragment.OnQuoteInteractionListener] interface
 * to handle interaction events.
 * Use the [QuoteSlidePagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuoteSlidePagerFragment : Fragment() {

    private lateinit var viewModel: QuoteViewModel

    private lateinit var binding: FragmentQuoteSlidePagerBinding

    private var quote: Quote? = null


    private var listener: OnQuoteInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quote = it.getParcelable(ARG_CURRENT_QUOTE)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuoteViewModel::class.java)
        quote?.let { viewModel.bind(it) }

        binding.viewModel = viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quote_slide_pager, container, false)
        binding.favBtn.setOnClickListener {
            onFavoritePressed(quote, binding)
        }

        binding.shareBtn.setOnClickListener {
            onShareQuote(quote)
        }
        return binding.root
    }

    private fun onShareQuote(quote: Quote?) {
        listener?.onShareInteraction(quote!!)
    }

    private fun onFavoritePressed(quote: Quote?, binding: FragmentQuoteSlidePagerBinding) {
        quote?.favorite = !quote?.favorite!!

        viewModel.bind(quote)
        binding.invalidateAll()
        listener?.onFavoriteInteraction(quote)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnQuoteInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnQuoteInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnQuoteInteractionListener {
        fun onFavoriteInteraction(
            quote: Quote?
        )

        fun onShareInteraction(quote: Quote?)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: Quote) =
            QuoteSlidePagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CURRENT_QUOTE, param1)
                }
            }
    }
}
