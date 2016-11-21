package com.requireproxy.assist;

import com.jaynewstrom.requireproxy.runtime.Proxy;
import com.jaynewstrom.requireproxy.runtime.RequireProxy;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.build.IClassTransformer;
import javassist.build.JavassistBuildException;

public class RequireProxyProcessor implements IClassTransformer {

    public RequireProxyProcessor() {
    }

    @Override public boolean shouldTransform(CtClass candidateClass) throws JavassistBuildException {
        return isSupported(candidateClass);
    }

    private boolean isSupported(CtClass candidateClass) {
        try {
            for (CtClass candidateInterface : candidateClass.getInterfaces()) {
                if (candidateInterface.getName().equals(Proxy.class.getName())) {
                    return true;
                }
            }
        } catch (NotFoundException e) {
            // This is throwing an exception when a compile time generated class is passed into it.
        }

        return false;
    }

    @Override public void applyTransformations(CtClass classToTransform) throws JavassistBuildException {
        try {
            Set<CtMethod> methodSet = getRequireProxyMethods(classToTransform.getClassPool(), classToTransform.getName());
            addHasProxyCheck(methodSet.toArray(new CtMethod[methodSet.size()]));
        } catch (Exception e) {
            throw new JavassistBuildException(e);
        }
    }

    private Set<CtMethod> getRequireProxyMethods(ClassPool pool, String className) throws NotFoundException {
        Set<CtMethod> methodSet = new LinkedHashSet<>();
        Collections.addAll(methodSet, pool.get(className).getMethods());
        Collections.addAll(methodSet, pool.get(className).getDeclaredMethods());
        Set<CtMethod> requireProxyMethods = new LinkedHashSet<>();
        for (CtMethod method : methodSet) {
            if (method.hasAnnotation(RequireProxy.class)) {
                requireProxyMethods.add(method);
            }
        }
        return requireProxyMethods;
    }

    private void addHasProxyCheck(CtMethod[] methods) throws CannotCompileException, NotFoundException {
        for (CtMethod method : methods) {
            method.insertBefore("{if (!hasProxy()) { return; } }");
        }
    }
}