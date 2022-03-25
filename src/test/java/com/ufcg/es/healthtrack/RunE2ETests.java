package com.ufcg.es.healthtrack;

import com.ufcg.es.healthtrack.e2e.colesterol.RunnerColesterolTest;
import com.ufcg.es.healthtrack.e2e.fezes.RunnerFezesTest;
import com.ufcg.es.healthtrack.e2e.glicemia.RunnerGlicemiaTest;
import com.ufcg.es.healthtrack.e2e.hemograma.RunnerHemogramaTest;
import com.ufcg.es.healthtrack.e2e.pressao.RunnerPressaoTest;
import com.ufcg.es.healthtrack.e2e.urina.RunnerUrinaTest;
import com.ufcg.es.healthtrack.e2e.usuario.RunnerUsuarioTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({RunnerUsuarioTest.class,
        RunnerGlicemiaTest.class,
        RunnerColesterolTest.class,
        RunnerPressaoTest.class,
        RunnerFezesTest.class,
        RunnerUrinaTest.class,
        RunnerHemogramaTest.class})

public class RunE2ETests {
}
