package com.requireproxy.assist

import com.github.stephanenicolas.morpheus.AbstractMorpheusPlugin
import javassist.build.IClassTransformer
import org.gradle.api.Project

public class RequireProxyPlugin extends AbstractMorpheusPlugin {

    @Override public IClassTransformer[] getTransformers(Project project) {
        return new RequireProxyProcessor();
    }

    @Override protected void configure(Project project) {
        // TODO: Maybe find a good way to bring in transitive deps.
    }

    @Override protected Class getPluginExtension() {
        RequireProxyPluginExtension
    }

    @Override protected String getExtension() {
        "requireproxy"
    }
}