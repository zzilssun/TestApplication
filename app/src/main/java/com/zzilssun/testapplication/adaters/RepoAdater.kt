package com.zzilssun.testapplication.adaters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zzilssun.testapplication.R
import com.zzilssun.testapplication.models.RepoModel
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoAdater : RecyclerView.Adapter<RepoAdater.ViewHolder>() {
    private val items = ArrayList<RepoModel>()

    fun putItems(items: List<RepoModel>) {
        val positionStart = this.items.size
        val itemCount = items.size
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.run {
            items[holder.adapterPosition].run {
                txt_repo_name.text = name
                txt_repo_description.text = description

                txt_star.text = stargazers_count.toString()
            }

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}