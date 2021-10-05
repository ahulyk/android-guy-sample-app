package net.hulyk.androidapp.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import net.hulyk.androidapp.R
import net.hulyk.androidapp.databinding.DetailsFragmentBinding
import net.hulyk.androidapp.ui.details.DetailsViewModel.State

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.details_fragment) {

    private val viewModel by viewModels<DetailsViewModel>()
    private val binding by viewBinding(DetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.drawFace()
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: State) = when (state) {
        State.Error -> {
            showProgress(false)
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
        State.Loading -> showProgress()
        is State.Success -> {
            showProgress(false)
            binding.image.setImageBitmap(state.data.bitmap)
        }
    }

    private fun showProgress(visible: Boolean = true) {
        binding.progress.isVisible = visible
    }

}