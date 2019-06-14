package com.simplekjl.tddkt.ui

/**
 * This class will allow us to control the states of the view through Live Data
 * at the same time is allowing us to have more control over the UI
 *
 * @author Jose Luis Crisostomo Sanchez
 * @see github.com/simplekjl/TDDKT
 * */

sealed class UiState

object Loading : UiState()

data class Success<T>(val data: T): UiState()

class ErrorMessage(val msg : String) :UiState()