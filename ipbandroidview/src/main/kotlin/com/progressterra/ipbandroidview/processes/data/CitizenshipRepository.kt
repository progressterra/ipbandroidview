package com.progressterra.ipbandroidview.processes.data

import com.progressterra.ipbandroidview.entities.Citizenship

interface CitizenshipRepository {

    suspend fun citizenships(): List<Citizenship>

    suspend fun idByName(name: String): String

    class Base : CitizenshipRepository {

        private val data = listOf(
            Citizenship(
                name = "Россия",
                id = "08db6340-c0c5-4b0b-8546-aeff7259b739"
            ),
            Citizenship(
                name = "Таджикистан",
                id = "08db6340-a968-41d2-8d83-76a1db8ad366"
            ),
            Citizenship(
                name = "Украина",
                id = "08db6340-a968-41d2-8d83-76a1db8ad366"
            ),
            Citizenship(
                name = "Узбекистан",
                id = "08db6340-a968-41d2-8d83-76a1db8ad366"
            ),
            Citizenship(
                name = "Беларусь",
                id = "08db6340-8152-4b24-8a32-776545e3240e"
            ),
            Citizenship(
                name = "Кыргызстан",
                id = "08db6340-8152-4b24-8a32-776545e3240e"
            ),
            Citizenship(
                name = "Казахстан",
                id = "08db6340-8152-4b24-8a32-776545e3240e"
            ),
            Citizenship(
                name = "Армения",
                id = "08db6340-8152-4b24-8a32-776545e3240e"
            )
        )

        override suspend fun idByName(name: String): String = data.first { it.name == name }.id

        override suspend fun citizenships(): List<Citizenship> = data
    }
}

