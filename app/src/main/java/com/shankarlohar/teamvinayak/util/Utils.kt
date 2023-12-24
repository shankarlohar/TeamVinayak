package com.shankarlohar.teamvinayak.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun lerp(start: Float, stop: Float, fraction: Float): Float =
        (1 - fraction) * start + fraction * stop


    val EaseOutBounce: Easing = Easing { fraction ->
        val n1 = 7.5625f
        val d1 = 2.75f
        var newFraction = fraction

        return@Easing if (newFraction < 1f / d1) {
            n1 * newFraction * newFraction
        } else if (newFraction < 2f / d1) {
            newFraction -= 1.5f / d1
            n1 * newFraction * newFraction + 0.75f
        } else if (newFraction < 2.5f / d1) {
            newFraction -= 2.25f / d1
            n1 * newFraction * newFraction + 0.9375f
        } else {
            newFraction -= 2.625f / d1
            n1 * newFraction * newFraction + 0.984375f
        }
    }
    val EaseOutQuart = CubicBezierEasing(0.25f, 1f, 0.5f, 1f)

    fun getCurrentDate():String{
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    }



    fun convertMillisToDateString(millis: Long, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern)
        val date = Date(millis)
        return dateFormat.format(date)
    }

    fun openWebsite(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle potential errors (e.g., no browser installed)
            Log.e("OpenWebsite", "Error opening website: $e")
        }
    }
    private fun verifyVerhoeffCheckDigit(number: String): Boolean {
        val d = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val p = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val inv = intArrayOf(0, 4, 3, 2, 1, 5, 6, 7, 8, 9)

        var c = 0
        for (i in number.length - 1 downTo 0) {
            c = d[c] + p[(number[i].toString().toInt())]
            c = c % 10
        }
        return c == 0
    }
    fun checkAadhaar(aadhaarNumber: String): Boolean{
        if (aadhaarNumber.isNotEmpty()){
            return aadhaarNumber.matches(Regex("^[2-9]{1}[0-9]{11}$")) &&
                    verifyVerhoeffCheckDigit(aadhaarNumber)
        }
        return false
    }

    private val EMAIL_REGEX = Regex(
        "^" +  // Start of string
                "[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +  // Username
                "@" +  // "@" symbol
                "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*" +  // Domain name
                "\$"  // End of string
    )

    fun checkEmail(email:String):Boolean{
        return EMAIL_REGEX.matches(email)
    }

    private val INDIA_MOBILE_REGEX = Regex(
        "^(\\+91)?" +  // Optional +91
                " ?[6789][0-9]{9}$"  // Start with 6, 7, 8 or 9 and follow with 9 digits
    )
    fun checkMobile(number: String): Boolean{
        return INDIA_MOBILE_REGEX.matches(number)
    }

}


enum class Role{
    ADMIN,
    MEMBER
}
enum class Details{
    APPROVED,
    UNDER_REVIEW
}
enum class Status{
    ACTIVE,
    PENDING,
    BLOCKED
}

enum class Steps{
    ONBOARD,
    FORM,
    CHOICE,
    CLIENT,
    OWNER
}

enum class UiStatus{
    Completed,
    Loading,
    Failed
}

sealed class LoginResult {
    object Success : LoginResult()
    data class Error(val error: String) : LoginResult()
}