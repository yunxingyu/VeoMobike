package com.veo.common.log

import android.util.Log

/**
 * Function: log管理类，本工程中所需log原则上统一调用本类，发布市场时，统一关闭log输出
 * VeoLog.d 不会写到文件中，其他的会写到文件中
 */
object VeoLog {
    var mLogger: ILog? = null

    /**
     *@param logDir 日志写入目录，请给单独的目录，除了日志文件不要把其他文件放入该目录，不然可能会被日志的自动清理功能清理掉。
     *@param cacheDir 缓存目录，当 logDir 不可写时候会写进这个目录，可选项，不选用请给 ""， 如若要给，建议给应用的 /data/data/packname/files/log 目录。
     * @param cacheDays  一般情况下填0即可。非0表示会在 _cachedir 目录下存放几天的日志。
     */
    @JvmStatic
    fun init(
        debug: Boolean = false,
        logDir: String,
        cacheDir: String,
        cacheDays: Int = 0
    ) {
        mLogger = if (debug) {
            DebugLog(logDir)
        } else {
            LogImpl(cacheDir, logDir, debug, cacheDays)
        }
    }

    private fun safeLog(block: () -> Unit) {
        try {
            block()
        } catch (e: Exception) {
            try {
                mLogger?.e("XLog", Log.getStackTraceString(e))
            } catch (e: Exception) {
            }
        }
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        safeLog { mLogger?.d(tag, msg) }
    }

    @JvmStatic
    fun d(tag: String, msg: String, throwable: Throwable?) {
        safeLog { mLogger?.d(tag, msg, throwable) }
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        safeLog { mLogger?.i(tag, msg) }
    }

    @JvmStatic
    fun i(tag: String, msg: String, throwable: Throwable?) {
        safeLog { mLogger?.i(tag, msg, throwable) }
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        safeLog { mLogger?.w(tag, msg) }
    }

    @JvmStatic
    fun w(tag: String, msg: String, throwable: Throwable?) {
        safeLog { mLogger?.w(tag, msg, throwable) }
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        safeLog { mLogger?.e(tag, msg) }
    }

    @JvmStatic
    fun e(tag: String, msg: String, throwable: Throwable?) {
        safeLog { mLogger?.e(tag, msg, throwable) }
    }
}