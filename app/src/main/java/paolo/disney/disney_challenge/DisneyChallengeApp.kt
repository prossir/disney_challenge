package paolo.disney.disney_challenge

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class DisneyChallengeApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initElegantDebugging()
        initBackwardsCompatibleTime()
    }

    private fun initElegantDebugging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initBackwardsCompatibleTime() {
        AndroidThreeTen.init(this)
    }

}