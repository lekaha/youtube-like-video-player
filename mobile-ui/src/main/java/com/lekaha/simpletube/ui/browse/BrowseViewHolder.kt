package com.lekaha.simpletube.ui.browse

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.ext.v
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem
import com.lekaha.simpletube.ui.view.recycler.ViewHolderBinder
import com.lekaha.simpletube.ui.view.recycler.ViewHolderFactory

class BrowseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var avatarImage: ImageView = view.findViewById(R.id.image_avatar)
    var nameText: TextView = view.findViewById(R.id.text_name)
    var titleText: TextView = view.findViewById(R.id.text_title)

    fun bind(simpletube: SimpletubeViewModel) {
        nameText.text = simpletube.name
        titleText.text = simpletube.title

        Glide.with(itemView.context)
            .load(simpletube.avatar)
            .apply(RequestOptions().placeholder(R.drawable.empty_image_holder))
            .into(avatarImage)

        itemView.setOnClickListener { simpletube.click() }
    }


    class BrowseViewHolderFactory constructor(context: Context) :
        ViewHolderFactory(context) {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            BrowseViewHolder(
                LayoutInflater
                    .from(context)
                    .inflate(R.layout.item_simpletube, parent, false)
            )

    }

    class BrowseViewHolderBinder : ViewHolderBinder {
        override fun bind(
            viewHolder: RecyclerView.ViewHolder,
            item: DisplayableItem<*>
        ) {
            val browseViewHolder = BrowseViewHolder::class.java.cast(viewHolder)
            val simpletubeViewModel = SimpletubeViewModel::class.java.cast(item.model())
            browseViewHolder.bind(simpletubeViewModel)
        }

    }
}