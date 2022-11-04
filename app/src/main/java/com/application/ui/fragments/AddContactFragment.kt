package com.application.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.application.databinding.FragmentAddContactBinding
import com.application.models.UserModel
import com.application.models.UserViewModel


class AddContactFragment : Fragment() {
    private lateinit var binding: FragmentAddContactBinding
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var userPhotoUri = Uri.parse("")

    //contract photo from gallery
    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            userPhotoUri = uri
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false).apply {
            btnSave.setOnClickListener { returnUserInfo() }
            btnAddPhoto.setOnClickListener {
                addPhoto()
                binding.ivAccPhoto.setImageURI(userPhotoUri)
            }
            imgBtnBack.setOnClickListener { goBack() }
        }
        return binding.root
    }


    private fun returnUserInfo() {
        with(binding) {
            if (etUsername.text?.toString()?.isEmpty() == false) {
                sharedViewModel.addUser(
                    UserModel(
                        sharedViewModel.getLastId() + 1,
                        userPhotoUri.toString(),
                        etUsername.text.toString(),
                        etCareer.text.toString(),
                        etEmail.text.toString(),
                        etPhone.text.toString(),
                        etAddress.text.toString(),
                        etDateBirth.text.toString()
                    )
                )
                findNavController().popBackStack()
            } else {
                tInUsername.error = "necessary field"
            }
        }
    }

    private fun addPhoto() {
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

}