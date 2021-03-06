package com.github.mreram.bazaartemplateplugin.module

import com.android.tools.idea.gradle.project.sync.GradleSyncInvoker
import com.github.mreram.bazaartemplateplugin.builders.module.module
import com.google.wireless.android.sdk.stats.GradleSyncStats
import com.intellij.jarRepository.settings.reloadAllRepositoryLibraries
import com.intellij.openapi.project.Project
import com.intellij.ui.wizard.WizardDialog
import java.lang.reflect.Field
import javax.swing.JButton

class ModuleBuilderDialog(
    private val project: Project,
    private val rootPath: String
) : WizardDialog<ModuleWizardModel>(
    true,
    ModuleWizardModel()
) {

    init {
        title = "New Bazaar Module"
        val myFinish: Field = javaClass.superclass.getDeclaredField("myFinish")
        myFinish.isAccessible = true
        (myFinish.get(this) as JButton).text = "Besaz"
    }

    override fun doOKAction() {
        super.doOKAction()
        module {
            project(project)
            name(ModuleConfig.name)
            nameCamelCase(ModuleConfig.nameCamelCase)
            rootPath(rootPath)
            hasDataSource(ModuleConfig.hasDataSource)
            hasDi(ModuleConfig.hasDi)
            hasView(ModuleConfig.hasView)
            hasActionLog(ModuleConfig.hasActionLog)
            hasNetwork(ModuleConfig.hasNetwork)
            componentType(ModuleConfig.componentType)
            hasViewModel(ModuleConfig.hasViewModel)
            hasWorker(ModuleConfig.hasWorker)
            hasStartupTask(ModuleConfig.hasStartupTask)
            hasFragment(ModuleConfig.hasFragment)
        }
        reloadAllRepositoryLibraries(project)
        GradleSyncInvoker.getInstance().requestProjectSync(
            project,
            GradleSyncInvoker.Request(
                GradleSyncStats.Trigger.TRIGGER_MODIFIER_ADD_MODULE_DEPENDENCY
            )
        )
    }
}