package com.github.mreram.bazaartemplateplugin.builders.module

import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleArgumentConfigs
import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleFileConfigs
import com.github.mreram.bazaartemplateplugin.codegenerator.TemplateGenerator
import com.intellij.openapi.project.Project
import java.io.File

fun module(builder: ModuleBuilder.() -> Unit) {
    val module = ModuleBuilder().apply(builder).build()
    TemplateGenerator().createTemplateFromResources(
        module.files,
        module.rootPath,
        pathArguments = ModuleArgumentConfigs.pathArguments(module),
        contentArguments = ModuleArgumentConfigs.contentArguments(module)
    )
    addModuleToSettingsGradle(module)
}

private fun addModuleToSettingsGradle(module: Module) {
    File(
        module.project.basePath,
        "settings.gradle"
    ).appendText("\ninclude ':${module.name}'")
}

data class Module(
    val rootPath: String,
    val name: String,
    val project: Project,
    val files: List<String>
)

class ModuleBuilder {

    private var name: String = ""
    private var rootPath: String = ""
    private var project: Project? = null
    private var hasDataSource: Boolean = false
    private var hasDi: Boolean = false
    private var hasNetwork: Boolean = false
    private var hasActionLog: Boolean = false
    private var hasView: Boolean = false

    fun rootPath(value: String) {
        rootPath = value
    }

    fun name(value: String) {
        name = value
    }

    fun project(value: Project) {
        project = value
    }

    fun hasDataSource(value: Boolean) {
        hasDataSource = value
    }

    fun hasDi(value: Boolean) {
        hasDi = value
    }

    fun hasNetwork(value: Boolean) {
        hasNetwork = value
    }

    fun hasView(value: Boolean) {
        hasView = value
    }

    fun hasActionLog(value: Boolean) {
        hasActionLog = value
    }

    fun build(): Module {
        val files = mutableListOf<String>()
        files += ModuleFileConfigs.baseModuleFilePaths
        if (hasDataSource) {
            files += ModuleFileConfigs.datasourceFilePaths
        }
        if (hasNetwork) {
            files += ModuleFileConfigs.networkFilePaths
        }
        if (hasDi) {
            files += ModuleFileConfigs.diFilePaths
        }
        if (hasView) {
            files += ModuleFileConfigs.viewFilePaths
        }
        if (hasActionLog) {
            files += ModuleFileConfigs.actionLogFilePaths
        }
        return Module(
            rootPath,
            name,
            requireNotNull(project),
            files
        )
    }
}