package com.ufcg.es.healthtrack.e2e.hemograma;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/hemograma", glue = "com/ufcg/es/healthtrack/e2e/hemograma", plugin = {"pretty"})
public class RunnerHemogramaTest {
}
