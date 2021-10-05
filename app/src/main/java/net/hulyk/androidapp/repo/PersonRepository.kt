package net.hulyk.androidapp.repo

import net.hulyk.androidapp.dto.PersonData

interface PersonRepository {

    fun getPersons(): List<PersonData>

}