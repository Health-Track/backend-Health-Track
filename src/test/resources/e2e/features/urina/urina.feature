Feature: Verificando se as funcionalidades do exame de urina funcionam corretamente

  Scenario: O cadastro de um exame de urina é realizado corretamente
    Given Existe um usuário com o email "email37@email.com" cadastrado
    And O usuário com o email "email37@email.com" está logado
    When É tentado cadastrar um novo exame de urina
    Then o exame é cadastrado corretamente

  Scenario: O cadastro de um exame de urina não é realizado devido ao usuário não estar logado
    Given Existe um usuário com o email "email31@email.com" cadastrado
    And O usuário com o email "email31@email.com" não está logado
    When É tentado cadastrar um novo exame de urina
    Then O exame não é cadastrado por o usuário não estar logado

  Scenario: A listagem dos exames funciona corretamente
    Given Existe um usuário com o email "email32@email.com" cadastrado
    And O usuário com o email "email32@email.com" está logado
    When É tentado listar todos os exames
    Then A listagem funciona

  Scenario: A listagem dos exames não funciona corretamente
    Given Existe um usuário com o email "email33@email.com" cadastrado
    And O usuário com o email "email33@email.com" não está logado
    When É tentado listar todos os exames
    Then A listagem não funciona por usuário não estar logado

  Scenario: A visualização de um exame funciona corretamente
    Given Existe um usuário com o email "email34@email.com" cadastrado
    And O usuário com o email "email34@email.com" está logado
    And Existe um exame cadastrado
    When É tentado visualizar um exame com id "2"
    Then A visualização funciona

  Scenario: A visualização de um não exame funciona corretamente devido ao exame não existir
    Given Existe um usuário com o email "email35@email.com" cadastrado
    And O usuário com o email "email35@email.com" está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona

  Scenario: A visualização de um não exame funciona corretamente devido usuário não estar logado
    Given Existe um usuário com o email "email36@email.com" cadastrado
    And O usuário com o email "email36@email.com" não está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona devido ao usuário não estar logado