package com.compiler

import com.annotation.RheaClient
import com.annotation.RheaHttp
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KotlinFile
import com.squareup.kotlinpoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import kotlin.properties.Delegates
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
/**
 * Created by catherine on 2017/7/6.
 */
class RheaHttpGenerator(val filer: Filer) {
    var element: TypeElement by Delegates.notNull<TypeElement>()
    fun generate() {
        println("this is RheaHttpGenerator's generate")
        val qualifiedName = element.qualifiedName.toString()
        val rheaHttp = element.getAnnotation(RheaHttp::class.java)
        val packageName = qualifiedName.substringBeforeLast(".")
        val newClassName = rheaHttp.name

        val fileBuilder = KotlinFile.builder(packageName, newClassName)
        val classBuilder = TypeSpec.objectBuilder(newClassName)
        element.enclosedElements.forEach {
            if (it is ExecutableElement) {
                val client = it.getAnnotation(RheaClient::class.java)
                classBuilder.addFun(FunSpec.builder(it.simpleName.toString())
                        .addCode(CodeBlock.Builder()
                                .addStatement("val client = %T.Builder().build()", OkHttpClient::class)
                                .addStatement("val retrofit = %T.Builder()", Retrofit::class)
                                .addBuilderStatement(".client(client)")
                                .addBuilderStatement(".baseUrl(%S)", client.url)
//                                .addBuilderStatement(".addConverterFactory(%T())", StringConverterFactory::class)
                                .addBuilderStatement(".addConverterFactory(%T.create())", GsonConverterFactory::class)
                                .addBuilderStatement(".addCallAdapterFactory(%T.create())", RxJava2CallAdapterFactory::class)
                                .addBuilderStatement(".build()")
                                .addStatement("return retrofit")
                                .build())
                        .returns(Retrofit::class)
                        .build())
            }
        }
        fileBuilder.addType(classBuilder.build())
        fileBuilder.skipJavaLangImports(true)
        val kotlinFile = fileBuilder.build()
        try {
            kotlinFile.writeTo(filer, packageName, newClassName)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}