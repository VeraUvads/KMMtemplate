package com.uva.kmm_template.android.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import co.touchlab.kermit.Logger
import java.io.Serializable

abstract class DestinationRule {

    open val requireArguments: List<NamedNavArgument> = emptyList()

    fun computeRoute(): String {
        check(requireArguments.none { it.argument.isNullable }) {
            "Right now doesn't support optional arguments"
        }
        check(!screenName.startsWith("/")) {
            "Screen name cannot start with '/'"
        }
        val args = requireArguments.joinToString(
            prefix = "/",
            separator = "/"
        ) { "{${it.name}}" }
        return "$screenName$args"
    }

    protected abstract val screenName: String

    private val arguments: ArrayList<Any> = arrayListOf() // todo think

    protected fun with(vararg any: Any): DestinationRule {
        arguments.add(any)
        return this
    }

    fun computeRouteWithArgs(): String {
        check(arguments.size >= requireArguments.filterNot { it.argument.isNullable }.size) {
            "Please check that you pass all not null arguments"
        }
        check(arguments.size <= requireArguments.size) {
            "Looks like, you pass extra arguments or forgot add it to requireArguments field"
        }
        check(arguments.none { it is Parcelable && it is Serializable }) {
            "Right now Navigation lib doesn't support Parcelable and Serializable"
        }
        val argsString = arguments
            .onEachIndexed { index, any ->
                val navType = navTypeByObject(any)
                val shouldNavType = requireArguments[index].argument.type

                if (navType == NavTypeAll) Logger.w("We get NavTypeAll from restricted API be careful")
//                check(navType == shouldNavType || navType == NavTypeAll) {
//                    "Argument with index $index has wrong type.\nExpect: $shouldNavType\nActual: $navType"
//                }
            }
            .joinToString(separator = "/", prefix = "/")

        return "$screenName$argsString"
    }

    @Suppress("RestrictedApi")
    private fun navTypeByObject(any: Any): NavType<*> {
        return try {
            NavType.inferFromValueType(any)
        } catch (ex: Exception) {
            NavTypeAll
        }
    }
}

private val NavTypeAll = object : NavType<Any>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Any = error("unsupported")

    override fun parseValue(value: String): Any = error("unsupported")

    override fun put(bundle: Bundle, key: String, value: Any) = error("unsupported")
}
