package edu.bth.ma.passthebomb.client.utils

import android.os.CountDownTimer


/**
 * Taken from: https://stackoverflow.com/questions/5738362/pause-countdowntimer-in-android-when-activity-is-not-in-front
 * This class uses the native CountDownTimer to
 * create a timer which could be paused and then
 * started again from the previous point. You can
 * provide implementation for onTick() and onFinish()
 * then use it in your projects.
 */
abstract class CountDownTimerPausable(
    millisInFuture: Long,
    countDownInterval: Long
) {
    var millisInFuture: Long = 0
    var countDownInterval: Long = 0
    var millisRemaining: Long = 0
    var countDownTimer: CountDownTimer? = null
    var isPaused = true

    private fun createCountDownTimer() {
        countDownTimer = object : CountDownTimer(millisRemaining, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                millisRemaining = millisUntilFinished
                this@CountDownTimerPausable.onTick(millisUntilFinished)
            }

            override fun onFinish() {
                this@CountDownTimerPausable.onFinish()
            }
        }
    }

    /**
     * Callback fired on regular interval.
     *
     * @param millisUntilFinished The amount of time until finished.
     */
    abstract fun onTick(millisUntilFinished: Long)

    /**
     * Callback fired when the time is up.
     */
    abstract fun onFinish()

    /**
     * Cancel the countdown.
     */
    fun cancel() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
        millisRemaining = 0
    }

    /**
     * Start or Resume the countdown.
     * @return CountDownTimerPausable current instance
     */
    @Synchronized
    fun start(): CountDownTimerPausable {
        if (isPaused) {
            createCountDownTimer()
            countDownTimer!!.start()
            isPaused = false
        }
        return this
    }

    /**
     * Pauses the CountDownTimerPausable, so it could be resumed(start)
     * later from the same point where it was paused.
     */
    fun pause() {
        if (isPaused == false) {
            countDownTimer!!.cancel()
        }
        isPaused = true
    }

    init {
        this.millisInFuture = millisInFuture
        this.countDownInterval = countDownInterval
        millisRemaining = this.millisInFuture
    }
}