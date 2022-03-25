Feature: Verificando se as funcionalidades do exame de glicemia funcionam corretamente

  Scenario: O cadastro de um exame de Glicemia é realizado corretamente
    Given Existe um usuário com o email "email1@email.com" cadastrado
    And O usuário com o email "email1@email.com" está logado
    When É tentado cadastrar um novo exame de glicemia
    Then o exame é cadastrado corretamente

  Scenario: O cadastro de um exame de glicemia não é realizado devido a data da medição já esta registrada
    Given Existe um usuário com o email "email2@email.com" cadastrado
    And O usuário com o email "email2@email.com" está logado
    When O usuário tenta cadastrar um exame de glicemia em uma data já cadastrada
    Then O exame não é cadastrado

  Scenario: O cadastro de um exame de glicemia não é realizado devido ao usuário não estar logado
    Given Existe um usuário com o email "email3@email.com" cadastrado
    And O usuário com o email "email3@email.com" não está logado
    When O usuário tenta cadastrar um exame de glicemia em uma data já cadastrada
    Then O exame não é cadastrado por o usuário não estar logado

  Scenario: A listagem dos exames funciona corretamente
    Given Existe um usuário com o email "email4@email.com" cadastrado
    And O usuário com o email "email4@email.com" está logado
    When É tentado listar todos os exames
    Then A listagem funciona

  Scenario: A listagem dos exames não funciona corretamente
    Given Existe um usuário com o email "email5@email.com" cadastrado
    And O usuário com o email "email5@email.com" não está logado
    When É tentado listar todos os exames
    Then A listagem não funciona por usuário não estar logado

  Scenario: A visualização de um exame funciona corretamente
    Given Existe um usuário com o email "email6@email.com" cadastrado
    And O usuário com o email "email6@email.com" está logado
    And Existe um exame cadastrado
    When É tentado visualizar um exame com id "3"
    Then A visualização funciona

  Scenario: A visualização de um não exame funciona corretamente devido ao exame não existir
    Given Existe um usuário com o email "email7@email.com" cadastrado
    And O usuário com o email "email7@email.com" está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona

  Scenario: A visualização de um não exame funciona corretamente devido usuário não estar logado
    Given Existe um usuário com o email "email8@email.com" cadastrado
    And O usuário com o email "email8@email.com" não está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona devido ao usuário não estar logado

