package com.github.mreram.bazaartemplateplugin.module

import com.intellij.openapi.ui.Messages
import com.intellij.ui.wizard.WizardDialog

class ModuleBuilderDialog : WizardDialog<ModuleWizardModel>(true, ModuleWizardModel()) {

    init {
        title = "New Bazaar Module"
    }

    override fun doOKAction() {
        super.doOKAction()
        Messages.showMessageDialog("Babaaaa vaysa", "Wait", null)
    }
}