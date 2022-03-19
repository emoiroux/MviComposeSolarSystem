package block.interview.takehome.etiennemoiroux.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import block.interview.takehome.etiennemoiroux.presentation.employees.EmployeesViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : MviState, I : MviIntent>(private val defaultDispatcher: CoroutineDispatcher) :
    ViewModel() {
    val intent: Channel<I> = Channel()
    private val _state: MutableLiveData<S> = MutableLiveData()
    val state: LiveData<S> = _state

    init {
        observeIntents()
        viewModelScope.launch(defaultDispatcher) {
            updateState { getInitialState() }
        }
    }

    protected suspend fun updateState(handler: suspend () -> S) {
        val newState = handler()
        Log.i(EmployeesViewModel::class.simpleName, newState.name)
        _state.postValue(newState)
    }

    abstract fun getInitialState(): S

    abstract fun processIntents(intent: I)

    private fun observeIntents() {
        viewModelScope.launch(defaultDispatcher) {
            intent.consumeAsFlow().collect {
                processIntents(it)
            }
        }
    }
}