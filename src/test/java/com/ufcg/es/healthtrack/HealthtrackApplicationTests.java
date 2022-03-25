package com.ufcg.es.healthtrack;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        RunUnitTests.class,
        RunE2ETests.class})
class HealthtrackApplicationTests {


}
