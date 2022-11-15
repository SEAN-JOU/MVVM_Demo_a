package com.jwiseinc.onedayticket


import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.jwiseinc.onedayticket.utils.MD5Util
import com.jwiseinc.onedayticket.view.activtiy.LoginActivity
import com.jwiseinc.onedayticket.view.activtiy.MainActivity
import com.sray.pigeonmap.utils.SharedPreferencesUtil
import kotlinx.coroutines.delay
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.jwiseinc.onedayticket", appContext.packageName)
        Log.d("aaaaaa","test")
    }

    @Test
    fun loginTest() {
        launchActivity<LoginActivity>().use { scenario ->
            scenario.onActivity { activity ->
                activity.rememberCheckBox.performClick()
                activity.viewModel.login(activity.memberIDEdt.text.toString(),
                    MD5Util.md5(activity.memberIDEdt.text.toString() + activity.passwordEdt.text.toString())).observe(activity,{
                    Log.d("test_callback", "5")
                })
            }
        }
    }
}