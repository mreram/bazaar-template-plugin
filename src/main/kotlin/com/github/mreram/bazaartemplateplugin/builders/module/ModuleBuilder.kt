package com.github.mreram.bazaartemplateplugin.builders.module

import com.github.mreram.bazaartemplateplugin.builders.module.config.ModuleFileConfigs
import com.github.mreram.bazaartemplateplugin.builders.module.dagger.DaggerComponentType
import com.github.mreram.bazaartemplateplugin.builders.module.model.BazaarModule
import com.intellij.openapi.project.Project

class ModuleBuilder {

    private var name: String = ""
    private var nameCamelCase: String = ""
    private var rootPath: String = ""
    private var project: Project? = null
    private var hasDataSource: Boolean = false
    private var hasDi: Boolean = false
    private var hasNetwork: Boolean = false
    private var hasActionLog: Boolean = false
    private var hasView: Boolean = false
    private var componentType: DaggerComponentType? = null
    private var hasViewModel: Boolean = false
    private var hasFragment: Boolean = false
    private var hasWorker: Boolean = false
    private var hasStartupTask: Boolean = false

    fun rootPath(value: String) {
        rootPath = value
    }

    fun name(value: String) {
        name = value
    }

    fun nameCamelCase(value: String) {
        nameCamelCase = value
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

    fun hasViewModel(value: Boolean) {
        hasViewModel = value
    }

    fun hasWorker(value: Boolean) {
        hasWorker = value
    }

    fun hasStartupTask(value: Boolean) {
        hasStartupTask = value
    }

    fun hasFragment(value: Boolean) {
        hasFragment = value
    }

    fun hasView(value: Boolean) {
        hasView = value
    }

    fun hasActionLog(value: Boolean) {
        hasActionLog = value
    }

    fun componentType(value: DaggerComponentType?) {
        componentType = value
    }

    fun build(): BazaarModule {
        val projectFiles = buildStructure() + buildDagger()
        val componentTypeName = if (componentType == DaggerComponentType.ExposeComponent) {
            "Expose"
        } else {
            "Dispatcher"
        }
        return BazaarModule(
            rootPath,
            name,
            nameCamelCase,
            componentTypeName,
            requireNotNull(project),
            projectFiles
        )
    }

    private fun buildStructure(): MutableList<String> {
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
        return files
    }

    private fun buildDagger(): MutableList<String> {
        val files = mutableListOf<String>()
        if (hasDi) {
            files += ModuleFileConfigs.daggerComponentFiles
        }
        if (hasViewModel) {
            files += ModuleFileConfigs.daggerViewModelFiles
        }
        if (hasFragment) {
            files += ModuleFileConfigs.daggerFragmentFiles
        }
        if (hasNetwork) {
            files += ModuleFileConfigs.daggerNetworkFiles
        }
        if (hasStartupTask) {
            files += ModuleFileConfigs.daggerStartupTaskFiles
        }
        if (hasWorker) {
            files += ModuleFileConfigs.daggerWorkerFiles
        }
        return files
    }
}