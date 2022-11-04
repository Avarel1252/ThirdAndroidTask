package com.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.R
import com.application.databinding.UserItemBinding
import com.application.utils.extensions.setImage
import com.application.models.UserModel


class UserAdapter(private val listener: Listener) :
    ListAdapter<UserModel, UserAdapter.UserViewHolder>(ItemCallback), View.OnClickListener {

    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun deleteItem(user: UserModel)
        fun getSelectItemId(user: UserModel)
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btn_delete -> {
                    listener.deleteItem(it.tag as UserModel)
                }
                else -> {
                    listener.getSelectItemId(it.tag as UserModel)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.btnDelete.setOnClickListener(this)
        binding.root.setOnClickListener(this)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding) {
            root.tag = user
            btnDelete.tag = user

            tvUsername.text = user.username
            tvCareer.text = user.career
            ivAccIcon.setImage(user)
        }
    }

    object ItemCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

}