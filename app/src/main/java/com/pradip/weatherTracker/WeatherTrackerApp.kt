package com.pradip.weatherTracker

import android.app.Application
import com.pradip.weatherTracker.core.ApplicationScope
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import javax.inject.Inject


@HiltAndroidApp
class WeatherTrackerApp : Application() {
    @Inject
    @ApplicationScope
    lateinit var applicationScope: CoroutineScope

    override fun onTerminate() {
        applicationScope.cancel()
        super.onTerminate()
    }
}
