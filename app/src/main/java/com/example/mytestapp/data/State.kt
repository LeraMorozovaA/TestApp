package com.example.mytestapp.data

sealed class State {
    object SucceededState : State()
    object LoggingState : State()
    object ErrorState : State()
}
