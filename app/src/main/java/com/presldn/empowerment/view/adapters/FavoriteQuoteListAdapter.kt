package com.presldn.empowerment.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.QuoteListItemBinding
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.viewmodels.QuoteViewModel

class FavoritesListAdapter : RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    private lateinit var favorites: List<Quote>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: QuoteListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.quote_list_item, parent,
                false)
        return ViewHolder(binding)    }

    override fun getItemCount(): Int {
        return if(::favorites.isInitialized) favorites.size else 0

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun updateFavorites(favorites:List<Quote>){
        this.favorites = favorites
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: QuoteListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = QuoteViewModel()

        fun bind(quote: Quote) {
            viewModel.bind(quote)
            binding.viewModel = viewModel
        }
    }

}