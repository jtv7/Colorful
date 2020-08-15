package com.vervelde.sample

import android.app.Application
import io.multimoon.colorful.Defaults
import io.multimoon.colorful.ThemeColor
import io.multimoon.colorful.initColorful

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val defaults = Defaults(
                primaryColor = ThemeColor.PURPLE,
                accentColor = ThemeColor.CYAN,
                useDarkTheme = false,
                translucent = false)
        initColorful(this, defaults)
    }
}