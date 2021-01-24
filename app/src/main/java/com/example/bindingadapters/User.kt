package com.example.bindingadapters

import android.view.View
import androidx.databinding.ObservableField

class User {
    // Singleton
    companion object {
        private var user: User? = null
        val instance: User
            get() {
                if (user == null) {
                    user = User()
                }
                return user!!
            }
    }

    // Observers
    val firstName = ObservableField<String>()
    val imageUrl = ObservableField<String>()
}