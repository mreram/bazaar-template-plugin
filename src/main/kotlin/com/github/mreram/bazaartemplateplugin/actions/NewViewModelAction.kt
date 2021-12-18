package com.github.mreram.bazaartemplateplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class NewViewModelAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        Messages.showErrorDialog(
            event.project,
            "It's not available now. Mikhay be jat code ham bezanam?",
            "Hmmmm?"
        )
    }
}