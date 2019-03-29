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

package ms.dew.devops.mojo;

import io.kubernetes.client.ApiException;
import ms.dew.devops.kernel.flow.preprare.PrepareFlowFactory;
import ms.dew.devops.kernel.function.NeedExecuteByGit;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

import java.io.IOException;

@Mojo(name = "preprare", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE)
@Execute(phase = LifecyclePhase.PACKAGE, goal = "preprare")
public class PreprareMojo extends BasicMojo {

    @Override
    protected boolean preExecute() throws MojoExecutionException, MojoFailureException, IOException, ApiException {
        NeedExecuteByGit.setNeedExecuteProjects(quiet);
        return true;
    }

    @Override
    protected boolean executeInternal() throws MojoExecutionException, MojoFailureException, IOException, ApiException {
        return PrepareFlowFactory.choose().exec(getMojoName());
    }
}
