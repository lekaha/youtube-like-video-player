package com.lekaha.simpletube.ui.ext

inline fun Boolean.ifTrue(block: () -> Unit) {
    if (this) {
        block()
    }
}

inline fun Boolean.ifFalse(block: () -> Unit) {
    if (!this) {
        block()
    }
}