package com.application.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.databinding.UserItemBinding
import com.application.models.UserModel
import com.application.utils.IUserAdapterListener
import com.application.utils.ItemCallbackDiffUtil
import com.application.utils.extensions.setImage


class UserAdapter(private val listener: IUserAdapterListener) :
    ListAdapter<UserModel, UserAdapter.UserViewHolder>(ItemCallbackDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel) {
            with(binding) {
                tvUsername.text = user.username
                tvCareer.text = user.career
                ivAccIcon.setImage(user)

                binding.btnDelete.setOnClickListener { listener.deleteItem(user) }
                binding.root.setOnClickListener { listener.getSelectItemId(user) }
            }
        }
    }
}