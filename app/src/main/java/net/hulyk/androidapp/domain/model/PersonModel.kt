package net.hulyk.androidapp.domain.model

import net.hulyk.androidapp.ui.main.recycler.PeopleDisplayableItem

class PersonModel(
    val name: String,
    val avatarImage: String,
    val faceSize: Float = 0f,
) : PeopleDisplayableItem