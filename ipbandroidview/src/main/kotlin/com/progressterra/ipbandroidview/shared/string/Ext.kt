package com.progressterra.ipbandroidview.shared.string

fun String.splitName( full: Boolean): List<String> {
            val splitName = this.trim().split(" ")
            if (splitName[0].isBlank() || splitName[1].isBlank())
                throw BadNameException()
            if (full)
                if (splitName[2].isBlank())
                    throw BadNameException()
            return splitName
        }