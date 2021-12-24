package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.wizard.WizardModel

class ModuleWizardModel : WizardModel("Bazaar Module Wizard") {
    init {
        add(ModuleWizardStructureStep())
        add(ModuleWizardDaggerStep())
        add(ModuleWizardFinalStep())
    }
}