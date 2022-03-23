package com.ufcg.es.healthtrack;


import com.ufcg.es.healthtrack.unit.service.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ColesterolServiceTest.class,
        ExameFezesServiceTest.class,
        ExameUrinaServiceTest.class})
class HealthtrackApplicationTests {


}
