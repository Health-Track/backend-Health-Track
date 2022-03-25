package com.ufcg.es.healthtrack.e2e.glicemia;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/glicemia", glue = "com/ufcg/es/healthtrack/e2e/glicemia", plugin = {"pretty"})
public class RunnerGlicemiaTest {
}
