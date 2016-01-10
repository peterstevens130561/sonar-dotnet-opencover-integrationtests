package com.stevpet.sonar.plugins.dotnet.integrationtests;


import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import com.stevpet.sonar.plugins.dotnet.mscover.DefaultMsCoverConfiguration;
import com.stevpet.sonar.plugins.dotnet.mscover.coveragetoxmlconverter.VsTestCoverageToXmlConverter;
import com.stevpet.sonar.plugins.dotnet.mscover.workflow.sensor.IntegrationTestCache;
import com.stevpet.sonar.plugins.dotnet.utils.vstowrapper.implementation.DefaultMicrosoftWindowsEnvironment;

public class OpenCoverIntegrationTestPlugin extends SonarPlugin {

    @Override
    public List getExtensions() {
        List exported=Arrays.asList(
        		DefaultMicrosoftWindowsEnvironment.class,
        		DefaultMsCoverConfiguration.class,       		
        		IntegrationTestCache.class,
                OpenCoverIntegrationTestSensor.class
                );
   
        return exported;
    }

}
