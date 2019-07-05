package com.presldn.empowerment.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.presldn.empowerment.R
import com.presldn.empowerment.databinding.QuoteListItemBinding
import com.presldn.empowerment.models.Quote
import com.presldn.empowerment.viewmodels.FavoriteListViewModel
import com.presldn.empowerment.viewmodels.QuoteViewModel


class FavoritesListAdapter(private val viewModel: FavoriteListViewModel, private val context: Context)
    : RecyclerView.Adapter<FavoritesListAdapter.ViewHolder>() {

    private lateinit var favorites: List<Quote>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: QuoteListItemBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.quote_list_item, parent,
                false)
        return ViewHolder(binding, this)    }

    override fun getItemCount(): Int {
        return if(::favorites.isInitialized) favorites.size else 0

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    fun removeFavorite(quote: Quote) {
        viewModel.onRemoveFavorite(quote)
    }

    fun shareFavorite(quote: Quote) {
        val message = "${quote.message} - ${quote.author}"

        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Quote share")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, message)
        context.startActivity(Intent.createChooser(sharingIntent, "Share using"))
    }

    fun updateFavorites(favorites:List<Quote>){
        this.favorites = favorites
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: QuoteListItemBinding, private val adapter: FavoritesListAdapter)
        : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = QuoteViewModel()

        fun bind(quote: Quote) {
            viewModel.bind(quote)
            binding.viewModel = viewModel
            binding.adapter = adapter
        }
    }

}