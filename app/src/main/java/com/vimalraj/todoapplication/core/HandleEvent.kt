package com.vimalraj.todoapplication.core

open class HandleEvent<out SEALED_EVENT_CLASS>(private val eventContent: SEALED_EVENT_CLASS) {

    var isAlreadyEventHandled = false
        private set

    fun getEventHandling(): SEALED_EVENT_CLASS? {
        return if (isAlreadyEventHandled) {
            null
        } else {
            isAlreadyEventHandled = true
            eventContent
        }
    }

    fun getContent(): SEALED_EVENT_CLASS = eventContent
}