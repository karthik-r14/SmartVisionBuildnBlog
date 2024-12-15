package com.mobileassistant.smartvisionbuildnblogui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobileassistant.smartvisionbuildnblogR
import com.mobileassistant.smartvisionbuildnblogmodel.MenuItem

class DashboardAdapter(context: Context, items: List<MenuItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<MenuItem> = ArrayList()
    private val ctx: Context

    init {
        this.items = items
        ctx = context
    }

    inner class TileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView
        var tileLayout: View

        init {
            image = view.findViewById(R.id.image) as ImageView
            title = view.findViewById(R.id.title) as TextView
            tileLayout = view.findViewById(R.id.tileLayout) as View
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid_card, parent, false)
        viewHolder = TileViewHolder(view)
        return viewHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TileViewHolder) {
            val item: MenuItem = items[position]
            holder.title.text = item.menuText
            holder.image.setImageDrawable(holder.image.context.resources.getDrawable(item.iconResId))
            holder.tileLayout.setOnClickListener {
                item.itemClickListener.invoke()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}