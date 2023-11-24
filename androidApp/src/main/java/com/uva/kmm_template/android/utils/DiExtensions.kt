@file:OptIn(KoinInternalApi::class, KoinInternalApi::class)

package com.uva.kmm_template.android.utils

import android.content.ComponentCallbacks
import org.koin.android.ext.android.get
import org.koin.android.logger.AndroidLogger
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext
import org.koin.core.instance.InstanceContext
import org.koin.core.qualifier.Qualifier

inline fun <reified T : Any> ComponentCallbacks.injectAll(
    qualifier: Qualifier? = null,
    mode: LazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED,
): Lazy<List<T>> {
    val koin = GlobalContext.get()
    val scope = koin.scopeRegistry.rootScope
    val context = InstanceContext(AndroidLogger(), scope)
    val all = koin.instanceRegistry.instances.values
        .filter { factory ->
            factory.beanDefinition.scopeQualifier == context.scope.scopeQualifier &&
                factory.beanDefinition.qualifier == qualifier
        }
        .filter { factory ->
            factory.beanDefinition.primaryType == T::class || factory.beanDefinition.secondaryTypes.contains(
                T::class,
            )
        }
        .distinct()
        .map { it.get(context) as T }
    return lazy(mode) { all }
}
