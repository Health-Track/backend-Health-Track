Feature: Verificando se as funcionalidades do exame de colesterol funcionam corretamente

  Scenario: O cadastro de um exame de colesterol é realizado corretamente
    Given Existe um usuário com o email "email10@email.com" cadastrado
    And O usuário com o email "email10@email.com" está logado
    When É tentado cadastrar um novo exame de colesterol
    Then o exame é cadastrado corretamente

  Scenario: O cadastro de um exame de colesterol não é realizado devido ao usuário não estar logado
    Given Existe um usuário com o email "email11@email.com" cadastrado
    And O usuário com o email "email11@email.com" não está logado
    When É tentado cadastrar um novo exame de colesterol
    Then O exame não é cadastrado por o usuário não estar logado

  Scenario: A listagem dos exames funciona corretamente
    Given Existe um usuário com o email "email12@email.com" cadastrado
    And O usuário com o email "email12@email.com" está logado
    When É tentado listar todos os exames
    Then A listagem funciona

  Scenario: A listagem dos exames não funciona corretamente
    Given Existe um usuário com o email "email13@email.com" cadastrado
    And O usuário com o email "email13@email.com" não está logado
    When É tentado listar todos os exames
    Then A listagem não funciona por usuário não estar logado

  Scenario: A visualização de um exame funciona corretamente
    Given Existe um usuário com o email "email14@email.com" cadastrado
    And O usuário com o email "email14@email.com" está logado
    And Existe um exame cadastrado
    When É tentado visualizar um exame com id "8"
    Then A visualização funciona

  Scenario: A visualização de um não exame funciona corretamente devido ao exame não existir
    Given Existe um usuário com o email "email15@email.com" cadastrado
    And O usuário com o email "email15@email.com" está logado
    When É tentado visualizar um exame com id "10"
    Then A visualização não funciona

  Scenario: A visualização de um não exame funciona corretamente devido usuário não estar logado
    Given Existe um usuário com o email "email16@email.com" cadastrado
    And O usuário com o email "email16@email.com" não está logado
    When É tentado visualizar um exame com id "10"
    Then A visualização não funciona devido ao usuário não estar logado