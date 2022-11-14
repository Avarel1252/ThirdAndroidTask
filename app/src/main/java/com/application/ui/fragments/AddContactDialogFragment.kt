package com.application.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.application.R
import com.application.databinding.FragmentAddContactBinding
import com.application.models.UserModel
import com.application.models.UserViewModel
import com.application.utils.Constants
import com.application.utils.extensions.setImageUriGlide


class AddContactDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentAddContactBinding
    private val sharedViewModel: UserViewModel by activityViewModels()
    private var userPhotoUri = Uri.parse(Constants.DEFAULT_USER_PHOTO)

    //contract photo from gallery
    private val pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
        if (uri != null) {
            userPhotoUri = uri
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setStyle(STYLE_NORMAL,R.style.MyDialog)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        with(binding) {
            btnSave.setOnClickListener { returnUserInfo() }
            btnAddPhoto.setOnClickListener { addPhoto() }
            imgBtnBack.setOnClickListener { goBack() }
        }
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
                dismiss()
            } else {
                tInUsername.error = getString(R.string.necessary_field)
            }
        }
    }

    private fun addPhoto() {
        pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        binding.ivAccPhoto.setImageUriGlide(userPhotoUri)
    }

    private fun goBack() {
        dismiss()
    }
}