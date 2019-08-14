package org.craftsrecords.talkadvisor.recommendation.criteria

import org.craftsrecords.talkadvisor.recommendation.preferences.Preferences
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver

class CriteriaParameterResolver : ParameterResolver {
    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        return parameterContext.parameter.type == Preferences::class.java || parameterContext.parameter.type == Criteria::class.java
    }

    override fun resolveParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Any {
        if (parameterContext.parameter.type == Criteria::class.java) {
            return createCriteria()
        }
        return createPreferences()
    }
}