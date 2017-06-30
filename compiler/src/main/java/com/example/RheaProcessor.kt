package com.example

import com.google.auto.service.AutoService
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * Created by catherine on 2017/6/30.
 */
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)//标识该处理器支持的源码版
class RheaProcessor : AbstractProcessor() {
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        println("this is a process test")
        return true
    }
}