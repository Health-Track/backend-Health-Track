package com.ufcg.es.healthtrack.e2e.usuario;

import com.ufcg.es.healthtrack.HealthtrackApplication;
import com.ufcg.es.healthtrack.model.Usuario;
import com.ufcg.es.healthtrack.model.dto.AlterarSenhaDTO;
import com.ufcg.es.healthtrack.model.dto.Credenciais;
import com.ufcg.es.healthtrack.model.dto.LoginResponse;
import com.ufcg.es.healthtrack.model.dto.UsuarioDTO;
import com.ufcg.es.healthtrack.repository.UsuarioRepository;
import com.ufcg.es.healthtrack.service.UsuarioService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = HealthtrackApplication.class
)
@ActiveProfiles("test")
public class E2EUsuarioTest {

    public static final String SENHA_USUARIO_VALIDO = "SenhaComMaisDeOito";
    @LocalServerPort
    private int port;

    @Inject
    @MockBean
    private UsuarioRepository usuarioRepository;


    private RequestSpecification request;

    private Response response;

    private String token;



    @Given("Não existe um usuário com o email {string} cadastrado")
    public void nãoExisteUmUsuárioComOEmailCadastrado(String email) {
        when(usuarioRepository.findById(email)).thenReturn(Optional.empty());
    }

    @When("É tentado cadastrar um novo usuário com o email {string}")
    public void umÉTentadoCadastrarUmNovoUsuárioComOEmail(String email) {

        request = given().
                contentType("application/json").
                body(criarUsuarioDTOValido(email)).
                port(port);
        response = request.when().post("/api/v1/auth");
    }

    private UsuarioDTO criarUsuarioDTOValido(String email) {
        return new UsuarioDTO(email,"Meu Nome", SENHA_USUARIO_VALIDO);
    }

    private Usuario criarUsuarioValido(String email) {
        return new Usuario(email,"Meu Nome", SENHA_USUARIO_VALIDO);
    }

    @Then("O cadastro do usuário é realizado com sucesso")
    public void oCadastroÉRealizadoComSucesso() {
        response.then().statusCode(201);
    }

    @Given("Existe um usuário com o email {string} cadastrado")
    public void existeUmUsuárioComOEmailCadastrado(String email) {
        when(usuarioRepository.findById(email)).thenReturn(Optional.of(criarUsuarioValido(email)));
    }

    @Then("O cadastro do ussuário falha")
    public void oCadastroDoUssuárioFalha() {
        response.then().statusCode(400).body("msg", equalTo("E-mail já cadastrado."));
    }

    @And("O usuário com o email {string} está logado")
    public void oUsuárioEstáLogado(String email) {
        LoginResponse lg = given().
            contentType("application/json").
            body(new Credenciais(email,SENHA_USUARIO_VALIDO)).port(port).
        when().post("api/v1/auth/login").getBody().as(LoginResponse.class);

        token = "Bearer " + lg.getToken();



    }

    @When("É tentado alterar a senha para uma nova senha válida")
    public void éTentadoAlterarASenhaParaUmaNovaSenhaVálida() {
        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(new AlterarSenhaDTO(SENHA_USUARIO_VALIDO,"NOVASENHAVALIDA")).port(port).
                when().put("api/v1/auth/alterar");
    }

    @Then("A senha é alterada corretamente")
    public void aSenhaÉAlteradaCorretamente() {
        response.then().statusCode(200);
    }

    @When("É tentado alterar a senha para uma nova senha válida utilizando a senha antiga errada")
    public void éTentadoAlterarASenhaParaUmaNovaSenhaVálidaUtilizandoASenhaAntigaErrada() {
        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).
                body(new AlterarSenhaDTO("SENHA ANTIGA INVALIDA","NOVASENHAVALIDA")).port(port).
                when().put("api/v1/auth/alterar");
    }

    @Then("A senha não é alterada")
    public void aSenhaNãoÉAlterada() {
        response.then().statusCode(400);
    }

    @And("O usuário não esta logado")
    public void oUsuárioNãoEstaLogado() {
        token = "";
    }

    @Then("Falha por o usuario não estar logado")
    public void aSenhaNãoÉAlteradaPorOUsuarioNãoEstarLogado() {
        response.then().statusCode(401);
    }

    @When("É tentado remover o usuario")
    public void éTentadoRemoverOUsuario() {
        response = given().
                contentType("application/json").
                header(new Header("Authorization", token)).port(port).
                when().delete("api/v1/auth/remover");
    }

    @Then("O usuário é removido")
    public void oUsuárioÉRemovido() {
        response.then().statusCode(200);
    }
}
