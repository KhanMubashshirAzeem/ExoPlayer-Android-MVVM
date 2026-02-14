package com.mubashshir.exoplayer_android_mvvm.ui.screens.base

sealed class UiEvent
{
    data class ShowError(val message: String) : UiEvent()
    object Unauthorized : UiEvent()
}

