package com.veo.common.log

//import com.tencent.mars.xlog.Log
//import com.tencent.mars.xlog.Xlog

/**
 * @author dujiajie
 **/
class LogImpl(cacheDir: String, logDir: String, debug: Boolean, cacheDays: Int = 0) : ILog {

    private var mLastFlushTime: Long = System.currentTimeMillis()

    init {
//        System.loadLibrary("c++_shared")
//        System.loadLibrary("marsxlog")
//        Log.setLogImp(Xlog())
//        Log.setConsoleLogOpen(debug)
//        Log.appenderOpen(
//            Log.LEVEL_INFO,
//            Xlog.AppednerModeAsync,
//            cacheDir,
//            logDir,
//            "autel",
//            cacheDays
//        )
    }

    override fun i(tag: String, msg: String, throwable: Throwable?) {
  //      Log.i(tag, msg, throwable)
        checkFlush()
    }

    override fun d(tag: String, msg: String, throwable: Throwable?) {
   //     Log.d(tag, msg, throwable)
        checkFlush()
    }

    override fun w(tag: String, msg: String, throwable: Throwable?) {
   //     Log.w(tag, msg, throwable)
        checkFlush()
    }

    override fun e(tag: String, msg: String, throwable: Throwable?) {
     //   Log.e(tag, msg, throwable)
        checkFlush()
    }

    override fun setConsoleLogOpen(isOpen: Boolean) {
  //      Log.setConsoleLogOpen(isOpen)
    }

    @Synchronized
    private fun checkFlush() {
        val current = System.currentTimeMillis()
        if (current - mLastFlushTime > 2000) {
            mLastFlushTime = current
 //           Log.appenderFlush()
        }
    }
}