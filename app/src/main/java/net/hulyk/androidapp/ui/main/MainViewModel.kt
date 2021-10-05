package net.hulyk.androidapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.hulyk.androidapp.domain.model.PersonModel
import net.hulyk.androidapp.domain.usecase.LoadPersonsUseCase
import net.hulyk.androidapp.ui.main.MainViewModel.State.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loadPersonsUseCase: LoadPersonsUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        loadPeople()
    }

    fun loadPeople() = viewModelScope.launch {
        _state.value = Loading
        loadPersonsUseCase()
            .fold({ _state.value = Success(it) }, { _state.value = Error })
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class Success(val data: List<PersonModel>) : State()
    }

}