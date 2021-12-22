package com.github.mreram.bazaartemplateplugin.builders.module.config

import com.github.mreram.bazaartemplateplugin.builders.module.Module

object ModuleArgumentConfigs {

    fun contentArguments(module: Module) = mapOf(
        "%MODULE_NAME%" to module.name,
        "%MODULE_NAME_CAMEL_CASE%" to module.nameCamelCase
    )

    fun pathArguments(module: Module) = mapOf(
        "modulename" to module.name,
        "%ModuleNameCamelCase%" to module.nameCamelCase
    )
}