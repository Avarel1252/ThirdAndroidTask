package com.application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.application.R
import com.application.databinding.FragmentMyContactsBinding
import com.application.models.UserModel
import com.application.models.UserViewModel
import com.application.utils.Constants
import com.application.adapters.UserAdapter


class MyContactsFragment : Fragment() {

    private lateinit var binding: FragmentMyContactsBinding
    private val sharedViewModel: UserViewModel by activityViewModels()
    private val adapter by lazy {
        UserAdapter(object : UserAdapter.Listener {
            override fun deleteItem(user: UserModel) {
                sharedViewModel.deleteUser(user)
            }

            override fun getSelectItemId(user: UserModel) {
                findNavController().navigate(
                    R.id.action_myContactsFragment_to_detailViewFragment,
                    bundleOf(Constants.TARGET_USER_ID_KEY to user.id)
                )
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyContactsBinding.inflate(inflater, container, false)

        sharedViewModel.userLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        with(binding) {
            rwUsers.adapter = adapter
            tvAddContacts.setOnClickListener {
                findNavController().navigate(R.id.action_myContactsFragment_to_addContactFragment)
            }
        }

        return binding.root
    }

}