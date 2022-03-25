package com.ufcg.es.healthtrack.e2e.pressao;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/pressao", glue = "com/ufcg/es/healthtrack/e2e/pressao", plugin = {"pretty"})
public class RunnerPressaoTest {
}
