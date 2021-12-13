package com.github.mreram.bazaartemplateplugin.builders

import com.github.mreram.bazaartemplateplugin.codegenerator.TemplateGenerator

private const val BASE_PATH_TEMPLATES_MODULE = "/templates/module"

fun module(builder: ModuleBuilder.() -> Unit) {
    val fileNames: Array<String> = arrayOf(
        "$BASE_PATH_TEMPLATES_MODULE/.gitignore.ft",
        "$BASE_PATH_TEMPLATES_MODULE/build.gradle.ft",
        "$BASE_PATH_TEMPLATES_MODULE/src/main/AndroidManifest.xml.ft",
    )

    val module = ModuleBuilder().apply(builder).build()
    TemplateGenerator().createTemplateFromResources(fileNames, module.destination)
}

data class Module(val destination: String, val name: String)

class ModuleBuilder {

    private var name: String = ""
    private var destination: String = ""

    fun destination(value: String) {
        destination = value
    }

    fun name(value: String) {
        name = value
    }

    fun build(): Module {
        return Module(name, destination)
    }
}