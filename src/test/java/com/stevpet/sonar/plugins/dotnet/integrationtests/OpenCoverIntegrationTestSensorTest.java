package com.stevpet.sonar.plugins.dotnet.integrationtests;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.scan.filesystem.PathResolver;

import com.stevpet.sonar.plugins.dotnet.mscover.MsCoverConfiguration;
import com.stevpet.sonar.plugins.dotnet.mscover.workflow.sensor.IntegrationTestCache;
import com.stevpet.sonar.plugins.dotnet.utils.vstowrapper.MicrosoftWindowsEnvironment;

public class OpenCoverIntegrationTestSensorTest {

	@Mock private MsCoverConfiguration msCoverConfiguration;
	@Mock private MicrosoftWindowsEnvironment microsoftWindowsEnvironment;
	@Mock private FileSystem fileSystem;
	@Mock private PathResolver pathResolver;
	@Mock private IntegrationTestCache cache;

	@Before
	public void before() {
		org.mockito.MockitoAnnotations.initMocks(this);
	}
	@Test
	public void OpenCoverIntegrationTestSensorInstantiation() {
		try {
		new OpenCoverIntegrationTestSensor(msCoverConfiguration, microsoftWindowsEnvironment, fileSystem, pathResolver, cache);
		} catch (Exception e ) {
			fail("constructor should not do anything, but apparently did something that caused it to fail " + e.getMessage());
		}
	}
}
