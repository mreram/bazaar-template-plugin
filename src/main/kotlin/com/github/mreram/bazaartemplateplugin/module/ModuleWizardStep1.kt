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
import java.awt.Dimension
import javax.swing.JComponent
import javax.swing.JPanel

class ModuleWizardStep1 : WizardStep<WizardModel>() {

    private lateinit var nameTextField: JBTextField
    private lateinit var datasourceCheckBox: JBCheckBox
    private lateinit var actionLogCheckBox: JBCheckBox
    private lateinit var networkCheckBox: JBCheckBox
    private lateinit var viewCheckBox: JBCheckBox
    private lateinit var diCheckBox: JBCheckBox
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
            datasourceCheckBox = JBCheckBox("Datasource").also { add(it) }
            actionLogCheckBox = JBCheckBox("Action log").also { add(it) }
            networkCheckBox = JBCheckBox("Network api").also { add(it) }
            viewCheckBox = JBCheckBox("View").also { add(it) }
            diCheckBox = JBCheckBox("Dependency injection").also { add(it) }
            errorLabel = JBLabel().also { it.foreground = JBColor.RED }
            add(errorLabel)
            return dialogPanel.apply {
                preferredSize = Dimension(500, 500)
            }
        }
    }

    override fun onNext(model: WizardModel?): WizardStep<*> {
        ModuleConfig.name = nameTextField.text
        ModuleConfig.hasActionLog = actionLogCheckBox.isSelected
        ModuleConfig.hasDataSource = datasourceCheckBox.isSelected
        ModuleConfig.hasNetwork = networkCheckBox.isSelected
        ModuleConfig.hasView = viewCheckBox.isSelected
        ModuleConfig.hasDi = diCheckBox.isSelected
        return super.onNext(model)
    }
}