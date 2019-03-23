/*
 * Copyright 2019. the original author or authors.
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
 */

package com.tairanchina.csp.dew.kernel.flow;

import com.tairanchina.csp.dew.kernel.Dew;
import io.kubernetes.client.ApiException;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.IOException;

public abstract class BasicFlow {

    public static final String FLAG_KUBE_RESOURCE_GIT_COMMIT = "dew.ms/git-commit";

    public final boolean exec() throws ApiException, IOException, MojoExecutionException {
        Dew.log.debug("Executing " + this.getClass().getSimpleName());
        if (!preProcess()) {
            Dew.log.debug("Finished,because [preProcess] is false");
            return false;
        }
        if (!process()) {
            Dew.log.debug("Finished,because [process] is false");
            return false;
        }
        if (!postProcess()) {
            Dew.log.debug("Finished,because [postProcess] is false");
            return false;
        }
        return true;
    }

    abstract protected boolean process() throws ApiException, IOException, MojoExecutionException;

    protected boolean preProcess() throws ApiException, IOException, MojoExecutionException {
        return true;
    }

    protected boolean postProcess() throws ApiException, IOException, MojoExecutionException {
        return true;
    }

}
