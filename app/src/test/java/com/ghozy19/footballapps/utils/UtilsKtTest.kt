package com.ghozy19.footballapps.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun changeFormatDate() {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("10/27/2018")
        assertEquals("Sabtu, 27 Oktober 2018", changeFormatDate(date))
    }

}