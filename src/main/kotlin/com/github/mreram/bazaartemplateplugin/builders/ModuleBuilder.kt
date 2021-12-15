package com.github.mreram.bazaartemplateplugin.builders

import com.github.mreram.bazaartemplateplugin.codegenerator.TemplateGenerator
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import java.io.File

private const val BASE_PATH_TEMPLATES_MODULE = "/templates/module"
private const val BASE_PATH_JAVA_FILES_MODULE =
    "$BASE_PATH_TEMPLATES_MODULE/src/main/java/com/farsitel/bazaar/module"

fun module(builder: ModuleBuilder.() -> Unit) {
    val baseModuleFileNames: Array<String> = arrayOf(
        "$BASE_PATH_TEMPLATES_MODULE/.gitignore.ft",
        "$BASE_PATH_TEMPLATES_MODULE/build.gradle.ft",
        "$BASE_PATH_TEMPLATES_MODULE/src/main/AndroidManifest.xml.ft"
    )
    val datasourceFileNames: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/datasource/",
        "$BASE_PATH_JAVA_FILES_MODULE/model/",
        "$BASE_PATH_JAVA_FILES_MODULE/request/",
        "$BASE_PATH_JAVA_FILES_MODULE/api/"
    )
    val daggerViewModelFileNames: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/module/"
    )
    val daggerStartupFileNames: Array<String> = arrayOf(
        "$BASE_PATH_JAVA_FILES_MODULE/viewmodel/"
    )

    val module = ModuleBuilder().apply(builder).build()
    val fileNamesToCreate = datasourceFileNames
    TemplateGenerator().createTemplateFromResources(
        fileNamesToCreate,
        module.destination,
        module.name
    )
    addModuleToSettingsGradle(module)
    Messages.showErrorDialog(module.project,"Mikhay be jat code ham bezanam?","Haa?")
}

private fun addModuleToSettingsGradle(module: Module) {
    File(
        module.project.basePath,
        "settings.gradle"
    ).appendText("\ninclude ':${module.name}'")
}

data class Module(val destination: String, val name: String, val project: Project)

class ModuleBuilder {

    private var name: String = ""
    private var destination: String = ""
    private var project: Project? = null
    private var hasDataSource: String = ""

    fun destination(value: String) {
        destination = value
    }

    fun name(value: String) {
        name = value
    }

    fun project(value: Project) {
        project = value
    }

    fun build(): Module {
        return Module(destination, name, requireNotNull(project))
    }
}