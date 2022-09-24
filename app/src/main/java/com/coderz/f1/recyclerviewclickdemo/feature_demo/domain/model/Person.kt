package com.coderz.f1.recyclerviewclickdemo.feature_demo.domain.model

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
) {
    val fullName: String = "$firstName $lastName"
}
