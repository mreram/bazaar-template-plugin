package com.github.mreram.bazaartemplateplugin.module

import com.github.mreram.bazaartemplateplugin.builders.module.dagger.DaggerComponentType
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBRadioButton
import com.intellij.ui.wizard.WizardModel
import com.intellij.ui.wizard.WizardNavigationState
import com.intellij.ui.wizard.WizardStep
import icons.Icons.dagger_icon
import org.jdesktop.swingx.HorizontalLayout
import org.jdesktop.swingx.VerticalLayout
import java.awt.BorderLayout
import java.awt.event.ItemListener
import javax.swing.Box
import javax.swing.ButtonGroup
import javax.swing.JComponent
import javax.swing.JPanel

class ModuleWizardDaggerStep : WizardStep<WizardModel>() {

    private var component: JComponent? = null
    private var daggerUi: JComponent? = null
    private var componentTypeButtonGroup: ButtonGroup? = null

    private lateinit var viewModelCheckBox: JBCheckBox
    private lateinit var networkCheckBox: JBCheckBox
    private lateinit var startupCheckBox: JBCheckBox
    private lateinit var workerCheckBox: JBCheckBox
    private lateinit var fragmentCheckBox: JBCheckBox

    override fun prepare(state: WizardNavigationState?): JComponent {
        if (component == null) {
            component = createUi()
        }
        return requireNotNull(component)
    }

    private fun createUi(): JComponent {
        val dialogPanel = JPanel(VerticalLayout().apply { gap = 5 })
        with(dialogPanel) {
            add(JPanel(HorizontalLayout().apply { gap = 5 }).also {
                val daggerCheckBox = JBCheckBox("Dagger is needed")
                daggerCheckBox.addItemListener(onChangeHasDagger(dialogPanel))
                it.add(daggerCheckBox)
                it.add(JBLabel(dagger_icon))
            })
            return dialogPanel
        }
    }

    private fun onChangeHasDagger(panel: JPanel) = ItemListener {
        if ((it.source as JBCheckBox).isSelected) {
            daggerUi = createDaggerUi(panel)
        } else {
            panel.remove(daggerUi)
        }
        panel.updateUI()
    }

    private fun createDaggerUi(panel: JPanel): JComponent {
        val layoutManager = VerticalLayout().apply { gap = 5 }
        return JPanel(layoutManager).apply {
            add(Box.createVerticalStrut(10))
            add(JBLabel("Dagger Component type:"), BorderLayout.LINE_START)
            componentTypeButtonGroup = ButtonGroup()
            componentTypeButtonGroup?.add(JBRadioButton("ExposeComponent").also {
                it.actionCommand = "ExposeComponent"
            })
            componentTypeButtonGroup?.add(JBRadioButton("DispatcherComponent").also {
                it.actionCommand = "ExposeComponent"
            })
            for (radioButton in componentTypeButtonGroup!!.elements) {
                add(radioButton)
            }
            add(Box.createVerticalStrut(10))
            add(JBLabel("Modules that you need to select:"), BorderLayout.LINE_START)
            viewModelCheckBox = JBCheckBox("ViewModel module").also { add(it) }
            fragmentCheckBox = JBCheckBox("Fragment module").also { add(it) }
            networkCheckBox = JBCheckBox("Network module").also { add(it) }
            startupCheckBox = JBCheckBox("Startup module").also { add(it) }
            workerCheckBox = JBCheckBox("Worker module").also { add(it) }
            panel.add(this)
        }
    }

    override fun onNext(model: WizardModel?): WizardStep<*> {
        val selectedActionCommand = (componentTypeButtonGroup?.selection?.actionCommand)
        ModuleConfig.componentType = if (selectedActionCommand == DaggerComponentType.ExposeComponent.name) {
            DaggerComponentType.ExposeComponent
        } else {
            DaggerComponentType.DispatcherComponent
        }
        ModuleConfig.hasViewModel = viewModelCheckBox.isSelected
        ModuleConfig.hasNetwork = networkCheckBox.isSelected
        ModuleConfig.hasStartupTask = startupCheckBox.isSelected
        ModuleConfig.hasWorker = workerCheckBox.isSelected
        ModuleConfig.hasFragment = fragmentCheckBox.isSelected
        return super.onNext(model)
    }
}