package com.android.expensetracker.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.android.expensetracker.models.Recurrence

data class ReportsState(
  val recurrence: Recurrence = Recurrence.Weekly,
  val recurrenceMenuOpened: Boolean = false
)

class ReportsViewModel: ViewModel() {
  private val _uiState = MutableStateFlow(ReportsState())
  val uiState: StateFlow<ReportsState> = _uiState.asStateFlow()

  fun setRecurrence(recurrence: Recurrence) {
    _uiState.update { currentState ->
      currentState.copy(
        recurrence = recurrence
      )
    }
  }

  fun openRecurrenceMenu() {
    _uiState.update { currentState ->
      currentState.copy(
        recurrenceMenuOpened = true
      )
    }
  }

  fun closeRecurrenceMenu() {
    _uiState.update { currentState ->
      currentState.copy(
        recurrenceMenuOpened = false
      )
    }
  }
}