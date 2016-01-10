package com.stevpet.sonar.plugins.dotnet.integrationtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sonar.test.TestUtils;

import com.stevpet.sonar.plugins.dotnet.mscover.MsCoverConfiguration;
import com.stevpet.sonar.plugins.dotnet.mscover.coverageparsers.vstestcoverageparser.FilteringCoverageParser;

import com.stevpet.sonar.plugins.dotnet.mscover.model.sonar.SonarCoverage;

public class OpenCoverFilteringCoverageParserTest {
	private FilteringCoverageParser parser;
	private File coverageFile;
	private SonarCoverage sonarCoverage;
	@Mock private MsCoverConfiguration msCoverConfiguration;

	@Before
	public void before() {
		org.mockito.MockitoAnnotations.initMocks(this);
		sonarCoverage = new SonarCoverage();
		coverageFile = TestUtils.getResource("OpenCoverFilteringCoverageParser/coverage-report.xml");
		assertNotNull(coverageFile);
		parser = new OpenCoverFilteringCoverageParser(msCoverConfiguration);
		org.mockito.MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void sampleFile_NoFiltering_ExpectSourceFiles() {
		//equal to not specifying any module
		parser.setModulesToParse(null);
		
		parser.parse(sonarCoverage, coverageFile);
		
		assertEquals("expect all 210 sourcefiles in assembly to be included",210,sonarCoverage.getValues().size());
	}
	
	@Test
	public void sampleFile_FilterApplied_ExpectSourceFiles() {
		//specify modules to parse
		List<String> modules = new ArrayList<String>();
		modules.add("joaGridder3D.UnitTest.dll");
		parser.setModulesToParse(modules);
		
		parser.parse(sonarCoverage, coverageFile);
		
		assertEquals("expect all 86 sourcefiles in assembly to be included",86,sonarCoverage.getValues().size());		
	}
	
	@Test
	public void sampleFile_FilterAppliedTwice_ExpectSourceFiles() {
		//specify modules to parse
		List<String> modules = new ArrayList<String>();
		modules.add("joaGridder3D.UnitTest.dll");
		modules.add("joaGridder3DAddin.UnitTest.dll");
		parser.setModulesToParse(modules);
		
		parser.parse(sonarCoverage, coverageFile);
		
		assertEquals("expect all 91 sourcefiles in assembly to be included",91,sonarCoverage.getValues().size());		
	}
	
	@Test
	public void sampleFile_FilterAppliedOnAssemblyNotInCoverage_ExpectNoSourceFiles() {
		//specify modules to parse
		List<String> modules = new ArrayList<String>();
		modules.add("bogus.exe");
		parser.setModulesToParse(modules);
		
		parser.parse(sonarCoverage, coverageFile);	
		
		assertEquals("expect no sourcefiles in assembly to be included",0,sonarCoverage.getValues().size());		
	}
}
