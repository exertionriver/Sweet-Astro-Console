package console.render

import console.render.RenderConsole

object RenderValidations {

    const val isValidString = "<valid>"

    private fun getRenderIsValidString(isNotValidString : String, isValid : Boolean, report : Boolean) : String {

        if (!isValid)
            return when (report) {
                true -> RenderConsole.error(isNotValidString)
                false -> isNotValidString
            }

        return isValidString
    }

    fun getRenderIsValidDateString(dateString : String, isValid : Boolean, report : Boolean) : String {

        val isNotValidString = "invalid date entry: $dateString"

        return getRenderIsValidString(isNotValidString, isValid, report)
    }

    fun getRenderIsValidTimeString(timeString : String, isValid : Boolean, report : Boolean) : String {

        val isNotValidString = "invalid time entry: $timeString"

        return getRenderIsValidString(isNotValidString, isValid, report)
    }


    fun getRenderIsValidAnyString(anyString : String, isValid : Boolean, report : Boolean) : String {

        val isNotValidString = "invalid entry: $anyString"

        return getRenderIsValidString(isNotValidString, isValid, report)
    }
}