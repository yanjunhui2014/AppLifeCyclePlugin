package com.milo.lifecycle

import com.milo.lifecycle.process.DexClassProcess
import com.milo.lifecycle.process.DexProcess
import com.milo.lifecycle.utils.PluginUtils
import com.milo.lifecycle.utils.PluginLog
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class LifeCyclePlugin implements Plugin<Project> {

    long startTime = 0

    @Override
    void apply(Project target) {
        startTime = System.currentTimeMillis()

        target.afterEvaluate {
            target.android.applicationVariants.each { variant ->

                // Gradle 1.5 - 2.x
                Task dexTask = target.tasks.findByName("transformClassesWithDexFor${variant.name.capitalize()}")
                if (dexTask == null) {
                    // Gradle 3.0+
                    dexTask = target.tasks.findByName("transformClassesWithDexBuilderFor${variant.name.capitalize()}")
                }
                if (dexTask == null) {
                    throw new NullPointerException("gradle版本不支持，未找到transformClassesWithDexFor, 目前仅支持1.5-3.0+")
                }

                def dexProcess = { DexProcess process, File file ->
                    if (process.shouldProcess()) {
                        process.process()
                    }
                }

                Task lifeCyclePrintTask = target.task("lifeCyclePrintTask${variant.name.capitalize()}") {
                    doLast {

                        Set<File> inputFiles = dexTask.inputs.files.files
                        Set<File> files = PluginUtils.getFiles(inputFiles)

                        files.each { file ->
                            if (file == null) {
                                return
                            }

                            if (file.name.endsWith(".jar")) {

                            } else if (file.name.endsWith(".class")) {
                                dexProcess.call(new DexClassProcess(), file)
                            }
                        }

                    }
                }

                dexTask.dependsOn(lifeCyclePrintTask)
            }
        }

        PluginLog.i("LifeCyclePlugin执行完毕，共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
    }

}