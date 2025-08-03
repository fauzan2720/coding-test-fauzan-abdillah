package com.example.codingtest_fauzanabdillah.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.codingtest_fauzanabdillah.R
import com.example.codingtest_fauzanabdillah.features.authentication.presentation.activities.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    // menguji apakah login berhasil dan mengirim username
    @Test
    fun loginSuccess() {
        onView(withId(R.id.et_username)).perform(typeText("Fauzan Abdillah"), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(typeText("12345678"), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        onView(withText("Selamat datang, Fauzan Abdillah!")).check(matches(isDisplayed()))
    }
}
