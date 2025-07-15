package com.example.exporter

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import java.io.File
import java.nio.charset.StandardCharsets

class ExportAction : AnAction() {
    override fun update(e: AnActionEvent) {
        val element = e.getData(LangDataKeys.PSI_ELEMENT)
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)
        val enabled =
            element is PsiMethod ||
                (element is PsiClass && element.isInterface) ||
                (file != null && !file.isDirectory && (file.extension == "http" || file.extension == "api"))
        e.presentation.isEnabledAndVisible = enabled
    }

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val element = e.getData(LangDataKeys.PSI_ELEMENT)
        val file = e.getData(CommonDataKeys.VIRTUAL_FILE)

        val choice = Messages.showChooseDialog(
            project,
            "Choose export format",
            "Export Interface",
            arrayOf("Markdown", "HTML"),
            "Markdown",
            null
        ) ?: return

        val infos = when {
            element is PsiMethod -> listOf(InterfaceInfo(element.name, element.containingFile.virtualFile.path, "METHOD"))
            element is PsiClass && element.isInterface -> listOf(InterfaceInfo(element.name, element.containingFile.virtualFile.path, "INTERFACE"))
            file != null && !file.isDirectory && (file.extension == "http" || file.extension == "api") -> parseApiFile(file)
            else -> return
        }

        val outputDir = File(project.basePath ?: return, "interface-docs")
        outputDir.mkdirs()

        if (infos.isEmpty()) return

        if (choice == "HTML") {
            HtmlWriter.write(File(outputDir, "interfaces.html"), infos)
        } else {
            MarkdownWriter.write(File(outputDir, "interfaces.md"), infos)
        }
    }
}

data class InterfaceInfo(val name: String, val path: String, val type: String)

object HtmlWriter {
    fun write(file: File, interfaces: List<InterfaceInfo>) {
        file.printWriter().use { out ->
            out.println("<html><body><h1>Interfaces</h1><ul>")
            interfaces.forEach { out.println("<li>${'$'}{it.name} (${it.type}) - ${'$'}{it.path}</li>") }
            out.println("</ul></body></html>")
        }
    }
}

object MarkdownWriter {
    fun write(file: File, interfaces: List<InterfaceInfo>) {
        file.printWriter().use { out ->
            out.println("# Interfaces")
            interfaces.forEach { out.println("- ${'$'}{it.name} (${it.type}) - ${'$'}{it.path}") }
        }
    }
}

private fun parseApiFile(file: VirtualFile): List<InterfaceInfo> {
    val lines = String(file.contentsToByteArray(), StandardCharsets.UTF_8).lines()
    if (file.extension == "http") {
        val regex = Regex("^(GET|POST|PUT|DELETE|PATCH|HEAD|OPTIONS)\\s+(\\S+)")
        return lines.mapNotNull { line ->
            val match = regex.find(line.trim()) ?: return@mapNotNull null
            val name = match.groupValues[1] + " " + match.groupValues[2]
            InterfaceInfo(name, file.path, "HTTP")
        }
    }
    return lines.filter { it.isNotBlank() }
        .map { InterfaceInfo(it.trim(), file.path, "CUSTOM") }
}
