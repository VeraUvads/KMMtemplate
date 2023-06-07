package com.uva.kmm_template.android.utils

import org.koin.core.module.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.bind

// TODO (?single?); add qualifier
inline fun <reified T : Any> Module.intoSet(
    noinline obj: () -> T
): KoinDefinition<out T> {
    return single { obj() } bind T::class
}
