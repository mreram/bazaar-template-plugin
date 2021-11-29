package com.github.mreram.bazaartemplateplugin.services

import com.intellij.openapi.project.Project
import com.github.mreram.bazaartemplateplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
