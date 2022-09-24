package com.coderz.f1.recyclerviewclickdemo.feature_demo.presentation

import com.coderz.f1.recyclerviewclickdemo.feature_demo.domain.model.Person

sealed class ClickedEvent {
    data class ItemClicked(val person: Person) : ClickedEvent()
}
