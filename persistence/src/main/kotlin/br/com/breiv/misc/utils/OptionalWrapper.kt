package br.com.breiv.misc.utils

import java.util.*

fun <T> Optional<T>.getIfPresent(): T? = if (this.isPresent) this.get() else null