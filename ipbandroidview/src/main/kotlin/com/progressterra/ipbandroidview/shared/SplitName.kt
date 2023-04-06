package com.progressterra.ipbandroidview.shared

interface SplitName {

    /**
     * @param full - is name, surname, patronymic if true or name and surname only if false
     */
    fun splitName(name: String, full: Boolean): List<String>

    class Base : SplitName {

        override fun splitName(name: String, full: Boolean): List<String> {
            val splitName = name.trim().split(" ")
            if (splitName[0].isBlank() || splitName[1].isBlank())
                throw BadNameException()
            if (full)
                if (splitName[2].isBlank())
                    throw BadNameException()
            return splitName
        }
    }
}