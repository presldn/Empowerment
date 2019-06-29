package com.presldn.empowerment.view.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
 * [QuoteSlidePagerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [QuoteSlidePagerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuoteSlidePagerFragment : Fragment() {

    private lateinit var viewModel: QuoteViewModel

    private lateinit var binding: FragmentQuoteSlidePagerBinding

    private var quote: Quote? = null
    private var listener: OnFragmentInteractionListener? = null

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

        return binding.root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment QuoteSlidePagerFragment.
         */
        @JvmStatic
        fun newInstance(param1: Quote) =
            QuoteSlidePagerFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_CURRENT_QUOTE, param1)
                }
            }
    }
}
