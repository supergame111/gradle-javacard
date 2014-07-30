/*
 * Copyright 2014 Fidesmo AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.fidesmo.gradle.javacard

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.plugins.JavaPlugin

class JavacardPlugin implements Plugin<Project> {

    void apply(Project project) {

         if (!project.plugins.hasPlugin(JavaPlugin)) {
             project.plugins.apply(JavaPlugin)
         }

        // configure java build
        project.sourceCompatibility = '1.2'
        project.targetCompatibility = '1.2'

        // register extension for building cap
        CapExtension capExtension = project.extensions.create(CapExtension.NAME, CapExtension)

        project.getTasks().create('cap', Cap.class) {
            dependsOn(project.compileJava)
            extension = capExtension
            javacardHome = '/home/yves/opt/java-card-sdk'
            classesDir = project.sourceSets.main.output.classesDir.getPath()
            capsDir = project.getBuildDir().getPath() + "${File.separator}caps"
        }
    }
}


class CapExtension {

    static final String NAME = "cap"

    String aid
    String sourcePackage
    Map<String, String> applets
    String version
}