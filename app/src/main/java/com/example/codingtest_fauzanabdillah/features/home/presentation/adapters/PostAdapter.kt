package com.example.codingtest_fauzanabdillah.features.home.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.databinding.LayoutPostItemBinding
import com.example.codingtest_fauzanabdillah.features.home.domain.models.PostModel

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val list: MutableList<PostModel> = ArrayList()
    var onBookmarkClicked: ((PostModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutPostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], position)

    inner class ViewHolder(private val binding: LayoutPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = itemView.context

        fun bind(data: PostModel, position: Int) {
            with(binding) {
                Glide.with(context)
                    .load(data.imageURL)
                    .error(R.drawable.ic_error_24)
                    .into(binding.ivPost)

                tvPostTitle.text = data.title
                tvPostBody.text = data.body

                ivBtnBookmark.setOnClickListener {
                    onBookmarkClicked?.invoke(data)
                    ivBtnBookmark.setImageResource(R.drawable.ic_bookmark_added_24)
                    //notifyDataSetChanged()
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<PostModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        this.list.clear()
    }
}