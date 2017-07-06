package com.annotation

/**
 * Created by catherine on 2017/7/5.
 * 这是描述Function的
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FUNCTION)
annotation class RheaClient(val url: String)