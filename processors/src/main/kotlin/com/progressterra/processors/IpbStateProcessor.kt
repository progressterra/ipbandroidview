package com.progressterra.processors

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import java.util.Locale

class IpbStateProcessor(
    private val codeGenerator: CodeGenerator, private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.warn("Trigger")
        val symbols = resolver.getSymbolsWithAnnotation("IpbState")
        symbols.forEach { symbol ->
            logger.warn("Trigger")

            if (symbol is KSClassDeclaration) {
                generateUpdateFunctions(symbol)
            }
        }

        return emptyList()
    }

    private fun generateUpdateFunctions(symbol: KSClassDeclaration) {
        val packageName = symbol.packageName.asString()
        val className = symbol.simpleName.asString()
        logger.warn("Generate for $className")
        val properties = symbol.primaryConstructor?.parameters ?: emptyList()
        val file = codeGenerator.createNewFile(
            Dependencies(false, symbol.containingFile!!), packageName, "${className}Generated"
        )

        file.bufferedWriter().use { writer ->
            writer.appendLine("package $packageName")
            writer.appendLine()
            properties.forEach { property ->
                val propName = property.name!!.asString()
                val propType = property.type.resolve().declaration.qualifiedName!!.asString()
                writer.appendLine("fun $className.update${propName.capitalize()}(new${propName.capitalize()}: $propType) = copy($propName = new${propName.capitalize()})")
            }
        }
    }

    private fun String.capitalize() =
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}