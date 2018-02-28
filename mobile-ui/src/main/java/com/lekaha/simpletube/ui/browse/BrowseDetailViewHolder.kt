package com.lekaha.simpletube.ui.browse

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.model.SimpletubeSectionViewModel
import com.lekaha.simpletube.ui.view.recycler.DisplayableItem
import com.lekaha.simpletube.ui.view.recycler.ViewHolderBinder
import com.lekaha.simpletube.ui.view.recycler.ViewHolderFactory

class BrowseDetailViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var nameText: TextView = view.findViewById(R.id.text_section)
    var titleText: TextView = view.findViewById(R.id.text_description)

    fun bind(simpletubeSection: SimpletubeSectionViewModel) {
        nameText.text = simpletubeSection.title
        titleText.text = simpletubeSection.description

        itemView.setOnClickListener { simpletubeSection.click() }
    }


    class BrowseDetailViewHolderFactory constructor(context: Context) :
        ViewHolderFactory(context) {

        override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            BrowseDetailViewHolder(
                LayoutInflater
                    .from(context)
                    .inflate(R.layout.item_simpletube_section, parent, false)
            )

    }

    class BrowseDetailViewHolderBinder : ViewHolderBinder {
        override fun bind(
            viewHolder: RecyclerView.ViewHolder,
            item: DisplayableItem<*>
        ) {
            val browseViewHolder = BrowseDetailViewHolder::class.java.cast(viewHolder)
            val simpletubeViewModel = SimpletubeSectionViewModel::class.java.cast(item.model())
            browseViewHolder.bind(simpletubeViewModel)
        }

    }
}