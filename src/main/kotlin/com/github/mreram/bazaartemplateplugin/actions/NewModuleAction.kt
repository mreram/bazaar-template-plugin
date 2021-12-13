package com.github.mreram.bazaartemplateplugin.actions

import com.android.tools.idea.gradle.project.sync.GradleSyncInvoker
import com.github.mreram.bazaartemplateplugin.module.ModuleBuilderDialog
import com.google.wireless.android.sdk.stats.GradleSyncStats
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys

class NewModuleAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val currentPath = event.getData(PlatformDataKeys.VIRTUAL_FILE)?.path ?: return
        ModuleBuilderDialog(currentPath).show()
        GradleSyncInvoker.getInstance().requestProjectSync(
            event.project!!,
            GradleSyncInvoker.Request(
                GradleSyncStats.Trigger.TRIGGER_GRADLEDEPENDENCY_ADDED
            )
        )
    }
}