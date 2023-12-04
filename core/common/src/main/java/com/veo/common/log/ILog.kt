package com.veo.common.log


/**
 * @author dujiajie
 **/

interface ILog {
    fun d(tag: String, msg: String, throwable: Throwable? = null)
    fun i(tag: String, msg: String, throwable: Throwable? = null)
    fun w(tag: String, msg: String, throwable: Throwable? = null)
    fun e(tag: String, msg: String, throwable: Throwable? = null)
    fun setConsoleLogOpen(isOpen: Boolean)
}