package com.ufcg.es.healthtrack.e2e.usuario;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/e2e/features/usuario", glue = "com/ufcg/es/healthtrack/e2e/usuario", plugin = {"pretty"})
public class RunnerUsuarioTest {
}
