package com.coderz.f1.recyclerviewclickdemo.feature_demo.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coderz.f1.recyclerviewclickdemo.feature_demo.domain.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    val clickedPerson: MutableLiveData<Person> = MutableLiveData()
    val people: MutableLiveData<ArrayList<Person>> = MutableLiveData()

    init {
        people.postValue(
            arrayListOf(
                Person(
                    firstName = "John",
                    lastName = "Doe",
                    phoneNumber = "555-5555"
                ),
                Person(
                    firstName = "Jane",
                    lastName = "Doe",
                    phoneNumber = "868-4894"
                )
            )
        )
    }

    fun onEvent(event: ClickedEvent) {
        when (event) {
            is ClickedEvent.ItemClicked -> clickedPerson.postValue(event.person.copy())
        }
    }
}