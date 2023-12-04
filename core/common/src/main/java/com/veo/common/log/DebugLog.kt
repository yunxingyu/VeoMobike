package com.veo.common.log

import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

class DebugLog(private val logDirectory: String) : ILog {
    //    val filePath = AutelDirPathUtils.getAppDir().plus(File.separator).plus("log.txt")
    private lateinit var fileWriter: FileWriter
    private var logFilePath: String
    private val threadPool = Executors.newSingleThreadExecutor()
    private val dayFormat = SimpleDateFormat("_yyyyMMdd", Locale.getDefault())
    private val suffix: String = ".xlog"

    init {
        Log.d("DebugLog", " $logDirectory")
        if (!File(logDirectory).exists()) {
            File(logDirectory).mkdirs()
        }

        val dayString = dayFormat.format(Date())
        logFilePath = logDirectory.plus(File.separator).plus(dayString).plus(suffix)
        try {//会偶现FileNotFoundException的异常，暂未复现
            fileWriter = FileWriter(File(logFilePath), true)
        } catch (e: Exception) {
            VeoLog.e("DebugLog", "init exception -> $e")
        }
    }

    override fun d(tag: String, msg: String, throwable: Throwable?) {
        Log.d(tag, msg, throwable)
    }

    override fun i(tag: String, msg: String, throwable: Throwable?) {
        writeLog("i", tag, msg, throwable)
        Log.i(tag, msg, throwable)
    }

    override fun w(tag: String, msg: String, throwable: Throwable?) {
        writeLog("w", tag, msg, throwable)
        Log.w(tag, msg, throwable)
    }

    override fun e(tag: String, msg: String, throwable: Throwable?) {
        writeLog("e", tag, msg, throwable)
        Log.e(tag, msg, throwable)
    }

    override fun setConsoleLogOpen(isOpen: Boolean) {
    }

    private fun writeLog(level: String, tag: String, msg: String, throwable: Throwable? = null) {
        threadPool.execute {
            try {
                val dayString = dayFormat.format(Date())
                val currentLogPath = logDirectory.plus(File.separator).plus(dayString).plus(suffix)
                if (currentLogPath != logFilePath) {
                    logFilePath = currentLogPath
                    fileWriter = FileWriter(File(logFilePath), true)
                } else if (!File(logFilePath).exists()) {
                    if (!File(logDirectory).exists()) {
                        File(logDirectory).mkdirs()
                    }
                    fileWriter = FileWriter(File(logFilePath), true)
                }
                val error: String = throwable?.let {
                    val sw = StringWriter()
                    val pw = PrintWriter(sw)
                    it.printStackTrace(pw)
                    pw.flush()
                    sw.toString()
                } ?: ""
                val format = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss:SSS")
                val date = format.format(Date())
                val info = StringBuffer(date)
                info.append(":$level $tag:$msg ${throwable?.toString() ?: ""}")
                    .append(System.lineSeparator())
                if (error.isNotEmpty()) {
                    info.append("\n").append(error)
                }
                fileWriter.write(info.toString())
                fileWriter.flush()
            } catch (e: Exception) {
                VeoLog.e("DebugLog", "e -> $e")
            }
        }
    }
}