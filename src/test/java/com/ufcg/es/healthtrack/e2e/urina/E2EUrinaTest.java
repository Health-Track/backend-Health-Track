package com.ufcg.es.healthtrack.e2e.urina;

import com.ufcg.es.healthtrack.HealthtrackApplication;
import com.ufcg.es.healthtrack.model.dto.Credenciais;
import com.ufcg.es.healthtrack.model.dto.LoginResponse;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.model.dto.pressao.PressaoDTO;
import com.ufcg.es.healthtrack.model.dto.urina.UrinaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = HealthtrackApplication.class
)
@ActiveProfiles("test")
public class E2EUrinaTest {

    @LocalServerPort
    private int port;

    private Response response;

    public static final String SENHA_USUARIO_VALIDO = "SenhaComMaisDeOito";

    private String token;

    private UsuarioDTO criarUsuarioDTOValido(String email) {
        return new UsuarioDTO(email,"Meu Nome", SENHA_USUARIO_VALIDO);
    }

    @Given("Existe um usuário com o email {string} cadastrado")
    public void existeUmUsuárioComOEmailCadastrado(String email) {

        given().
                contentType("application/json").
                body(criarUsuarioDTOValido(email)).
                port(port).
                when().post("/api/v1/auth").
                then().statusCode(201);
    }
    @And("O usuário com o email {string} está logado")
    public void oUsuárioEstáLogado(String email) {
        LoginResponse lg = given().
                contentType("application/json").
                body(new Credenciais(email,SENHA_USUARIO_VALIDO)).port(port).
                when().post("api/v1/auth/login").getBody().as(LoginResponse.class);

        token = "Bearer " + lg.getToken();
    }

    @When("É tentado cadastrar um novo exame de urina")
    public void éTentadoCadastrarUmNovoExameDeUrina() {
        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(new UrinaDTO("d", LocalDateTime.now(),
                        "s",1,"s","s",
                        "s","s","s","s",
                        "s","s",1,1,1,
                        "s","s","s","s","s"))
                .port(port).
                when().post("api/v1/exame/urina");
    }

    @Then("o exame é cadastrado corretamente")
    public void oExameÉCadastradoCorretamente() {
        response.then().statusCode(201);
    }


    @Then("O exame não é cadastrado")
    public void oExameNãoÉCadastrado() {
        response.then().statusCode(400);
    }

    @And("O usuário com o email {string} não está logado")
    public void oUsuárioComOEmailNãoEstáLogado(String email) {
        token = email;
    }

    @Then("O exame não é cadastrado por o usuário não estar logado")
    public void oExameNãoÉCadastradoPorOUsuárioNãoEstarLogado() {
        response.then().statusCode(401);
    }

    @When("É tentado listar todos os exames")
    public void éTentadoListarTodosOsExames() {
        response = given().
                header(new Header("Authorization", token)).port(port).
                when().get("api/v1/exame/urina/listar");
    }

    @Then("A listagem funciona")
    public void aListagemFunciona() {
        response.then().statusCode(200);
    }

    @Then("A listagem não funciona por usuário não estar logado")
    public void aListagemNãoFuncionaPorUsuárioNãoEstarLogado() {
        response.then().statusCode(401);
    }

    @When("É tentado visualizar um exame com id {string}")
    public void éTentadoVisualizarTodosOsExames(String id) {
        response = given().
                header(new Header("Authorization", token)).
                port(port).
                when().get("api/v1/exame/urina/"+id);
    }

    @And("Existe um exame cadastrado")
    public void existeUmExameCadastrado() {
        UrinaDTO exame = new UrinaDTO("d", LocalDateTime.now(),
                "s",1,"s","s",
                "s","s","s","s",
                "s","s",1,1,1,
                "s","s","s","s","s");

        given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(exame).port(port).
                when().post("api/v1/exame/urina");
    }

    @Then("A visualização funciona")
    public void aVisualizaçãoFunciona() {
        response.then().statusCode(200);
    }

    @Then("A visualização não funciona")
    public void aVisualizaçãoNãoFunciona() {
        response.then().statusCode(400);
    }

    @Then("A visualização não funciona devido ao usuário não estar logado")
    public void aVisualizaçãoNãoFuncionaDevidoAoUsuárioNãoEstarLogado() {
        response.then().statusCode(401);
    }
}
