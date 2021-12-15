package com.github.mreram.bazaartemplateplugin.module

import com.intellij.ui.JBColor
import com.intellij.ui.components.JBBox
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import org.jdesktop.swingx.VerticalLayout
import org.jetbrains.kotlin.idea.core.util.onTextChange
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel

class ModuleWizardStep1 : WizardStep<WizardModel>() {

    private lateinit var nameTextField: JBTextField
    private var component: JComponent? = null
    private var errorLabel: JBLabel? = null

    override fun prepare(state: WizardNavigationState): JComponent {
        if (component == null) {
            component = createUi(state)
        }
        return requireNotNull(component)
    }

    private fun createUi(state: WizardNavigationState): JComponent {
        state.NEXT.isEnabled = false
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        nameTextField = JBTextField()
        nameTextField.onTextChange {
            state.NEXT.isEnabled = nameTextField.text.isNotEmpty()
            if (nameTextField.text.any { it.isUpperCase() }) {
                errorLabel?.text = "Your module name must be lower case"
                state.NEXT.isEnabled = false
            } else {
                errorLabel?.text = ""
            }
        }
        with(dialogPanel) {
            add(JBLabel("Name:"), BorderLayout.LINE_START)
            add(nameTextField, BorderLayout.LINE_START)
            dialogPanel.add(JBBox.createVerticalStrut(8))
            add(JBLabel("Config your structure, you need to:"), BorderLayout.LINE_START)
            add(JBCheckBox("Datasource"))
            add(JBCheckBox("Action log"))
            add(JBCheckBox("Network api"))
            add(JBCheckBox("View"))
            add(JBCheckBox("Adapter"))
            errorLabel = JBLabel().also { it.foreground = JBColor.RED }
            add(errorLabel)
            return dialogPanel.apply {
                preferredSize = Dimension(500, 500)
            }
        }
    }

    override fun onNext(model: WizardModel?): WizardStep<*> {
        ModuleConfig.name = nameTextField.text
        return super.onNext(model)
    }
}