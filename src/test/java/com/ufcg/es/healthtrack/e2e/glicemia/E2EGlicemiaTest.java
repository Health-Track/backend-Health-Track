package com.ufcg.es.healthtrack.e2e.glicemia;

import com.ufcg.es.healthtrack.HealthtrackApplication;
import com.ufcg.es.healthtrack.e2e.usuario.E2EUsuarioTest;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.AlterarSenhaDTO;
import com.ufcg.es.healthtrack.model.dto.Credenciais;
import com.ufcg.es.healthtrack.model.dto.LoginResponse;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.model.dto.glicemia.GlicemiaDTO;
import com.ufcg.es.healthtrack.model.exame.Glicemia;
import com.ufcg.es.healthtrack.repository.GlicemiaRepository;
import com.ufcg.es.healthtrack.repository.UsuarioRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = HealthtrackApplication.class
)
@ActiveProfiles("test")
public class E2EGlicemiaTest {

    @LocalServerPort
    private int port;

    private Response response;

    public static final String SENHA_USUARIO_VALIDO = "SenhaComMaisDeOito";

    private String token;

    private UsuarioDTO criarUsuarioDTOValido(String email) {
        return new UsuarioDTO(email,"Meu Nome", SENHA_USUARIO_VALIDO);
    }

    @Given("Existe um usu??rio com o email {string} cadastrado")
    public void existeUmUsu??rioComOEmailCadastrado(String email) {

        given().
            contentType("application/json").
            body(criarUsuarioDTOValido(email)).
            port(port).
        when().post("/api/v1/auth").
        then().statusCode(201);
    }
    @And("O usu??rio com o email {string} est?? logado")
    public void oUsu??rioEst??Logado(String email) {
        LoginResponse lg = given().
                contentType("application/json").
                body(new Credenciais(email,SENHA_USUARIO_VALIDO)).port(port).
                when().post("api/v1/auth/login").getBody().as(LoginResponse.class);

        token = "Bearer " + lg.getToken();
    }

    @When("?? tentado cadastrar um novo exame de glicemia")
    public void ??TentadoCadastrarUmNovoExameDeGlicemia() {
        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(new GlicemiaDTO(1, LocalDateTime.now())).port(port).
                when().post("api/v1/exame/glicemia");
    }

    @Then("o exame ?? cadastrado corretamente")
    public void oExame??CadastradoCorretamente() {
        response.then().statusCode(201);
    }


    @When("O usu??rio tenta cadastrar um exame de glicemia em uma data j?? cadastrada")
    public void oUsu??rioTentaCadastrarUmExameDeGlicemiaEmUmaDataJ??Cadastrada() {
        GlicemiaDTO exame = new GlicemiaDTO(1, LocalDateTime.now());

        given().
            contentType("application/json").
            header(new Header("Authorization", token)).
            body(exame).port(port).
            when().post("api/v1/exame/glicemia");

        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(exame).port(port).
                when().post("api/v1/exame/glicemia");
    }

    @Then("O exame n??o ?? cadastrado")
    public void oExameN??o??Cadastrado() {
        response.then().statusCode(400);
    }

    @And("O usu??rio com o email {string} n??o est?? logado")
    public void oUsu??rioComOEmailN??oEst??Logado(String email) {
        token = email;
    }

    @Then("O exame n??o ?? cadastrado por o usu??rio n??o estar logado")
    public void oExameN??o??CadastradoPorOUsu??rioN??oEstarLogado() {
        response.then().statusCode(401);
    }

    @When("?? tentado listar todos os exames")
    public void ??TentadoListarTodosOsExames() {
        response = given().
                header(new Header("Authorization", token)).port(port).
                when().get("api/v1/exame/glicemia/listar");
    }

    @Then("A listagem funciona")
    public void aListagemFunciona() {
        response.then().statusCode(200);
    }

    @Then("A listagem n??o funciona por usu??rio n??o estar logado")
    public void aListagemN??oFuncionaPorUsu??rioN??oEstarLogado() {
        response.then().statusCode(401);
    }

    @When("?? tentado visualizar um exame com id {string}")
    public void ??TentadoVisualizarTodosOsExames(String id) {
        response = given().
                header(new Header("Authorization", token)).
                port(port).
                when().get("api/v1/exame/glicemia/"+id);
    }

    @And("Existe um exame cadastrado")
    public void existeUmExameCadastrado() {
        GlicemiaDTO exame = new GlicemiaDTO(1, LocalDateTime.now());

        given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(exame).port(port).
                when().post("api/v1/exame/glicemia");
    }

    @Then("A visualiza????o funciona")
    public void aVisualiza????oFunciona() {
        response.then().statusCode(200);
    }

    @Then("A visualiza????o n??o funciona")
    public void aVisualiza????oN??oFunciona() {
        response.then().statusCode(400);
    }

    @Then("A visualiza????o n??o funciona devido ao usu??rio n??o estar logado")
    public void aVisualiza????oN??oFuncionaDevidoAoUsu??rioN??oEstarLogado() {
        response.then().statusCode(401);
    }
}
