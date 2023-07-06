package com.reisdeveloper.githubapiconsumer.ext

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.safeNavigate(direction: Int, bundle: Bundle? = null, navOptions: NavOptions? = null) {
    currentDestination?.getAction(direction)?.run {
        navigate(direction, bundle, navOptions)
    }
}