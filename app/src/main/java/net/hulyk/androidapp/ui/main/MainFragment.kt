package net.hulyk.androidapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import net.hulyk.androidapp.R
import net.hulyk.androidapp.databinding.MainFragmentBinding
import net.hulyk.androidapp.ui.main.MainViewModel.State
import net.hulyk.androidapp.ui.main.recycler.PeopleListAdapter

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(MainFragmentBinding::bind)
    private val peopleListAdapter = PeopleListAdapter {
        val direction = MainFragmentDirections.actionMainFragmentToDetailsFragment(it.avatarImage)
        findNavController().navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            swipeLayout.setOnRefreshListener { viewModel.loadPeople() }
            recyclerView.adapter = peopleListAdapter
        }

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
            peopleListAdapter.items = state.data
            peopleListAdapter.notifyDataSetChanged()
        }
    }


    private fun showProgress(visible: Boolean = true) {
        binding.progress.isVisible = visible
    }
}