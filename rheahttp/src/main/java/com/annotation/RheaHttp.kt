package com.annotation

/**
 * Created by catherine on 2017/7/5.
 *
 * 这是描述Class的
 */

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS,AnnotationTarget.FILE)
annotation class RheaHttp (val name: String ="RetrofitFactory")