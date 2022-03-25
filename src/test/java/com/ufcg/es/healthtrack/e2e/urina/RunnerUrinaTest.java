package com.ufcg.es.healthtrack.e2e.urina;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/urina", glue = "com/ufcg/es/healthtrack/e2e/urina", plugin = {"pretty"})
public class RunnerUrinaTest {
}
