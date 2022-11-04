package com.application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.application.databinding.FragmentDetailViewBinding
import com.application.models.UserViewModel
import com.application.utils.Constants
import com.application.utils.extensions.setImage

class DetailViewFragment : Fragment() {
    private lateinit var binding: FragmentDetailViewBinding
    private val sharedViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailViewBinding.inflate(inflater, container, false)
        val targetUser =
            sharedViewModel.getUser(requireArguments().getInt(Constants.TARGET_USER_ID_KEY))
        targetUser?.let {
            with(binding) {
                ivAccPhoto.setImage(it)
                tvUsername.text = it.username
                tvCareer.text = it.career
                tvAddress.text = it.homeAddress
            }
        }
        return binding.root
    }
}