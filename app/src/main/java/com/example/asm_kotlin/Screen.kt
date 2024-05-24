package com.example.asm_kotlin

enum class Screen {
    SPLASH,
    SIGN_IN,
    SIGN_UP,
    BOTTOM,
    HOME,
    BOOKMARK,
    NOTIFICATION,
    PROFILE,
    CART,
    DETAIL,
}

sealed class Screens(val route : String) {
    object Splash : Screens(Screen.SPLASH.name)
    object SignIn : Screens(Screen.SIGN_IN.name)
    object SignUp : Screens(Screen.SIGN_UP.name)
    object Bottom : Screens(Screen.BOTTOM.name)
    object Home : Screens(Screen.HOME.name)
    object Bookmark : Screens(Screen.BOOKMARK.name)
    object Notification : Screens(Screen.NOTIFICATION.name)
    object Profile : Screens(Screen.PROFILE.name)
    object Cart : Screens(Screen.CART.name)
    object Detail : Screens(Screen.DETAIL.name)

}