package com.ufcg.es.healthtrack.e2e.fezes;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/fezes", glue = "com/ufcg/es/healthtrack/e2e/fezes", plugin = {"pretty"})
public class RunnerFezesTest {
}
