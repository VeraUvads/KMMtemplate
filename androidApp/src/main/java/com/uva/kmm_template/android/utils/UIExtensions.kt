package com.uva.kmm_template.android.utils

import android.os.Looper

fun checkMainThread() {
    check(Looper.myLooper() == Looper.getMainLooper())
}