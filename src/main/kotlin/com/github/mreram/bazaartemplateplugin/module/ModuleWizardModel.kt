package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.wizard.WizardModel

class ModuleWizardModel : WizardModel("Bazaar Module Wizard") {
    init {
        add(ModuleWizardStep1())
        add(ModuleWizardStep2())
        add(ModuleWizardStep3())
    }
}