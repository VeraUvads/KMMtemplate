package com.uva.kmm_template.android.utils

import org.koin.core.module.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.bind

// TODO (?single?); add qualifier
inline fun <reified T : Any> Module.intoSetSingle(
    qualifier: Qualifier? = null,
    noinline obj: () -> T
): KoinDefinition<out T> {
    return single(qualifier) { obj() } bind T::class
}
