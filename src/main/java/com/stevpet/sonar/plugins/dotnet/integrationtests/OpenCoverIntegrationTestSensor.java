package com.stevpet.sonar.plugins.dotnet.integrationtests;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.scan.filesystem.PathResolver;

import com.stevpet.sonar.plugins.dotnet.mscover.MsCoverConfiguration;
import com.stevpet.sonar.plugins.dotnet.mscover.coveragetoxmlconverter.VsTestCoverageToXmlConverter;
import com.stevpet.sonar.plugins.dotnet.mscover.workflow.sensor.IntegrationTestCache;
import com.stevpet.sonar.plugins.dotnet.utils.vstowrapper.MicrosoftWindowsEnvironment;

public class OpenCoverIntegrationTestSensor extends
		OpenCoverIntegrationTestSensorBase {

	public OpenCoverIntegrationTestSensor(
			MsCoverConfiguration msCoverConfiguration,
			MicrosoftWindowsEnvironment microsoftWindowsEnvironment,
			FileSystem fileSystem,
			PathResolver pathResolver,
			IntegrationTestCache cache) {
		super(msCoverConfiguration, microsoftWindowsEnvironment, cache, 
				new OpenCoverIntegrationTestCoverageReader(microsoftWindowsEnvironment,msCoverConfiguration,fileSystem),
				new IntegrationTestsCoverageSaver(microsoftWindowsEnvironment,pathResolver,fileSystem),
				new VsTestCoverageToXmlConverter(fileSystem));
	}

}
