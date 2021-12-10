package com.github.mreram.bazaartemplateplugin.base

data class Module(
    val name: String,
    val nameWithoutPrefix: String
) {
    override fun toString() = nameWithoutPrefix
}