package com.ufcg.es.healthtrack.e2e.colesterol;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/colesterol", glue = "com/ufcg/es/healthtrack/e2e/colesterol", plugin = {"pretty"})
public class RunnerColesterolTest {
}
