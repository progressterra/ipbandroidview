package com.progressterra.processors

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSPropertyDeclaration
import java.util.Locale

class IpbStateProcessor(
    private val codeGenerator: CodeGenerator, private val logger: KSPLogger
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val ipbStateSymbols =
            resolver.getSymbolsWithAnnotation("com.progressterra.processors.IpbState")
        ipbStateSymbols.forEach { symbol ->
            if (symbol is KSClassDeclaration) {
                generateUpdateFunctions(symbol)
            }
        }

        val ipbSubStateSymbols =
            resolver.getSymbolsWithAnnotation("com.progressterra.processors.IpbSubState")
        ipbSubStateSymbols.forEach { symbol ->
            if (symbol is KSPropertyDeclaration) {
                generateSubStateUpdateFunctions(symbol)
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

    private fun generateSubStateUpdateFunctions(property: KSPropertyDeclaration) {
        val parentSymbol = property.parentDeclaration as? KSClassDeclaration ?: return
        val packageName = parentSymbol.packageName.asString()
        val parentClassName = parentSymbol.simpleName.asString()
        logger.warn("Generate for parent $parentClassName")
        val subStateClassName = property.type.resolve().declaration.simpleName.asString()
        logger.warn("Parent prop name: $subStateClassName")
        val subStateProperties =
            (property.type.resolve().declaration as KSClassDeclaration).primaryConstructor?.parameters
                ?: emptyList()
        logger.warn("Parent props: $subStateProperties")
        val file = codeGenerator.createNewFile(
            Dependencies(false, parentSymbol.containingFile!!),
            packageName,
            "${parentClassName}${property.simpleName.asString().capitalize()}Generated"
        )
        logger.warn("file package name  $packageName")
        logger.warn("file name ${parentClassName}${property.simpleName.asString().capitalize()}Generated")


        file.bufferedWriter().use { writer ->
            writer.appendLine("package $packageName")
            writer.appendLine()

            subStateProperties.forEach { subStateProperty ->
                logger.warn("Generate 1 for parent prop $subStateProperty")

                val propName = subStateProperty.name!!.asString()
                val propType =
                    subStateProperty.type.resolve().declaration.qualifiedName!!.asString()
                val parentPropertyName = property.simpleName.asString()
                logger.warn("Generate 2 for parent prop $subStateProperty")

                writer.appendLine("fun $parentClassName.update${parentPropertyName.capitalize()}${propName.capitalize()}(new${propName.capitalize()}: $propType) = copy($parentPropertyName = $parentPropertyName.update${propName.capitalize()}(new${propName.capitalize()}))")
                logger.warn("fun $parentClassName.update${parentPropertyName.capitalize()}${propName.capitalize()}(new${propName.capitalize()}: $propType) = copy($parentPropertyName = $parentPropertyName.update${propName.capitalize()}(new${propName.capitalize()}))")

            }
        }
    }


    private fun String.capitalize() =
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}
