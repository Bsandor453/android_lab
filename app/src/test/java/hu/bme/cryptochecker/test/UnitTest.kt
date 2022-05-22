package hu.bme.cryptochecker.test

import androidx.test.core.app.ActivityScenario
import com.google.common.truth.Truth.assertThat
import hu.bme.cryptochecker.ui.main_view.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class MyActivityTest {

    @Test
    fun testMainActivityTitle() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity: MainActivity ->
                assertThat(activity.title).isEqualTo(
                    "CryptoChecker"
                )
            }
        }
    }

    @Test
    fun testMainActivityHasFragments() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            scenario.onActivity { activity: MainActivity ->
                assertThat(activity.supportFragmentManager.fragments.isEmpty()).isEqualTo(
                    false
                )
            }
        }
    }

}