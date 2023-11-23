package com.shankarlohar.teamvinayak.util

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