package com.uva.kmm_template.android.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
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
            prefix = "/", separator = "/"
        ) { "{${it.name}}" }
        return "$screenName$args"
    }

    protected abstract val screenName: String

    fun computeRouteWithArgs(vararg args: Any): String {
        check(args.size >= requireArguments.filterNot { it.argument.isNullable }.size) {
            "Please check that you pass all not null arguments"
        }
        check(args.size <= requireArguments.size) {
            "Looks like, you pass extra arguments or forgot add it to requireArguments field"
        }
        check(args.none { it is Parcelable && it is Serializable }) {
            "Right now Navigation lib doesn't support Parcelable and Serializable"
        }
        val argsString = args
            .onEachIndexed { index, any ->
                val navType = navTypeByObject(any)
                val shouldNavType = requireArguments[index].argument.type

                check(navType == shouldNavType || navType == NavTypeAll) {
                    "Argument with index $index has wrong type.\nExpect: $shouldNavType\nActual: $navType"
                }
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

fun DestinationRule.with(): String {
    return computeRouteWithArgs()
}

fun <T1 : Any> DestinationRule.with(arg: T1): String {
    return computeRouteWithArgs(arg)
}

fun <T1 : Any, T2 : Any> DestinationRule.with(arg1: T1, arg2: T2): String {
    return computeRouteWithArgs(arg1, arg2)
}

fun <T1 : Any, T2 : Any, T3 : Any> DestinationRule.with(arg1: T1, arg2: T2, arg3: T3): String {
    return computeRouteWithArgs(arg1, arg2, arg3)
}


private val NavTypeAll = object : NavType<Any>(isNullableAllowed = true) {
    override fun get(bundle: Bundle, key: String): Any = error("unsupported")

    override fun parseValue(value: String): Any = error("unsupported")

    override fun put(bundle: Bundle, key: String, value: Any) = error("unsupported")
}
