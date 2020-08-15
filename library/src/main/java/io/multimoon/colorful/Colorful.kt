package io.multimoon.colorful

import android.app.Application
import android.content.Context
import android.util.Log

var mInstance: ColorfulDelegate? = null
val primaryThemeKey: String = "primary_theme"
val accentThemeKey: String = "accent_theme"
val darkThemeKey: String = "dark_theme"
val customThemeKey: String = "custom_theme"
val translucentKey: String = "translucent"

enum class BaseTheme {
    THEME_MATERIAL,
    THEME_APPCOMPAT,
    THEME_MATERIAL_COMPONETS
}

fun Colorful(): ColorfulDelegate {
    mInstance?.let { return it }
    throw Exception("Colorful has not been initialized! Please call initColorful(app:Application) in your application class before working with Colorful!")
}

fun initColorful(app: Application, defaults: Defaults = Defaults(ThemeColor.INDIGO, ThemeColor.RED, true, true)) {
    val time: Long = System.currentTimeMillis()
    val prefs = app.getSharedPreferences(ThemeEditor.PREF_NAME, Context.MODE_PRIVATE)

    val primary: ThemeColorInterface = ThemeColorInterface.parse(prefs.getString(primaryThemeKey, defaults.primaryColor.themeName).orEmpty())
    val accent: ThemeColorInterface = ThemeColorInterface.parse(prefs.getString(accentThemeKey, defaults.accentColor.themeName).orEmpty())

    mInstance = ColorfulDelegate(
            primary,
            accent,
            prefs.getBoolean(darkThemeKey, defaults.useDarkTheme),
            prefs.getBoolean(translucentKey, defaults.translucent),
            prefs.getInt(customThemeKey, defaults.customTheme))

    Log.d("COLORFUL", "Colorful init in " + (System.currentTimeMillis() - time) + " milliseconds!")
}