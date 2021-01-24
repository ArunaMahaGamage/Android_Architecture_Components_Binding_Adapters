package com.example.bindingadapters

import android.view.View

class MyHandlers {

    fun changeProfile(view: View) {
        var user = User.instance
        user.firstName.set("Gamage")
    }
}