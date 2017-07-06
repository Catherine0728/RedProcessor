package com.example

import com.google.auto.service.AutoService
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

/**
 * Created by catherine on 2017/6/30.
 * 处理器
 */
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)//标识该处理器支持的源码版
@SupportedAnnotationTypes("com.example.RheaHttp") //用来指示注视处理器支持哪些注视类型的注视
class RheaProcessor : AbstractProcessor() {
    private lateinit var filer: Filer

    @Synchronized override fun init(processingEnvironment: ProcessingEnvironment) {

        super.init(processingEnvironment)
        filer = processingEnvironment.filer
    }


    override fun process(p0: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        println("this is a process test")
        val set = roundEnvironment!!.getElementsAnnotatedWith(RheaHttp::class.java)
        println("---------" + set)
        set.forEach {
            println("###" + it)

        }
        return true
    }
}