package com.themobilecoder.datasource

import android.os.Looper


val COUNTRIES = """
[
        {
            "country_name": "Afghanistan",
            "country_code": "AFG"
        },
        {
            "country_name": "Denmark",
            "country_code": "DEN"
        },
        {
            "country_name": "Ireland",
            "country_code": "IRL"
        },
        {
            "country_name": "Russian Federation",
            "country_code": "RUSS"
        },
        {
            "country_name": "Taiwan",
            "country_code": "TAI"
        }
]
""".trimIndent()

val CAPITALS = listOf("""{ "country_name": "Afghanistan", "capital": "Kabul" }""",
    """{ "country_name": "Denmark", "capital": "Copenhagen" }""",
    """{ "country_name": "Ireland", "capital": "Dublin" }""",
    """{ "country_name": "Russian Federation", "capital": "Moscow" }""",
    """{ "country_name": "Taiwan Federation", "capital": "Taipei" }""")

class SlowNetwork {

    fun getCountries(): String {
        if (Thread.currentThread().equals(Looper.getMainLooper().thread)) {
            throw Exception("Cannot call from main thread")
        }

        Thread.sleep(5000)
        return COUNTRIES
    }

    fun getCapitolFor(countryCode: String): String {
        if (Thread.currentThread().equals(Looper.getMainLooper().thread)) {
            throw Exception("Cannot call from main thread")
        }

        Thread.sleep(4000)
        return when (countryCode) {
            "AFG" -> {
                CAPITALS.get(0)
            }
            "DEN" -> {
                CAPITALS.get(1)
            }
            "IRL" -> {
                CAPITALS.get(2)
            }
            "RUSS" -> {
                CAPITALS.get(3)
            }
            "TAI" -> {
                CAPITALS.get(4)
            }
            else -> {
                "Unknown code"
            }
        }
    }
}