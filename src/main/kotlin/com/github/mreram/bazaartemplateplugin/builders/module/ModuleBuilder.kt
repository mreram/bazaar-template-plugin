package com.github.mreram.bazaartemplateplugin.builders.module

import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleArgumentConfigs
import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleFileConfigs
import com.github.mreram.bazaartemplateplugin.codegenerator.TemplateGenerator
import com.intellij.openapi.project.Project
import java.io.File

fun module(builder: ModuleBuilder.() -> Unit) {
    val module = ModuleBuilder().apply(builder).build()
    val filePathsToCreate = ModuleFileConfigs.baseModuleFilePaths
    TemplateGenerator().createTemplateFromResources(
        filePathsToCreate,
        module.rootPath,
        pathArguments = ModuleArgumentConfigs.pathArguments(module),
        contentArguments = ModuleArgumentConfigs.contentArguments(module)
    )
    addModuleToSettingsGradle(module)
//    Messages.showErrorDialog(module.project, "Mikhay be jat code ham bezanam?", "Haa?")
}

private fun addModuleToSettingsGradle(module: Module) {
    File(
        module.project.basePath,
        "settings.gradle"
    ).appendText("\ninclude ':${module.name}'")
}

data class Module(val rootPath: String, val name: String, val project: Project)

class ModuleBuilder {

    private var name: String = ""
    private var rootPath: String = ""
    private var project: Project? = null
    private var hasDataSource: Boolean = false

    fun rootPath(value: String) {
        rootPath = value
    }

    fun name(value: String) {
        name = value
    }

    fun project(value: Project) {
        project = value
    }

    fun build(): Module {
        return Module(rootPath, name, requireNotNull(project))
    }
}