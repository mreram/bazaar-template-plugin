package com.github.mreram.bazaartemplateplugin.builders.module

import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleArgumentConfigs
import com.github.mreram.bazaartemplateplugin.builders.module.model.BazaarModule
import com.github.mreram.bazaartemplateplugin.codegenerator.TemplateGenerator
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

private fun addModuleToSettingsGradle(module: BazaarModule) {
    val prefixModule = module.rootPath
        .replace(module.project.basePath.toString(), "")
        .replace("/", ":")

    File(
        module.project.basePath,
        "settings.gradle"
    ).appendText("\ninclude '${prefixModule}:${module.name}'")
}

