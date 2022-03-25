Feature: Verificando se as funcionalidades do exame de fezes funcionam corretamente

  Scenario: O cadastro de um exame de fezes é realizado corretamente
    Given Existe um usuário com o email "email17@email.com" cadastrado
    And O usuário com o email "email17@email.com" está logado
    When É tentado cadastrar um novo exame de fezes
    Then o exame é cadastrado corretamente

  Scenario: O cadastro de um exame de fezes não é realizado devido ao usuário não estar logado
    Given Existe um usuário com o email "email18@email.com" cadastrado
    And O usuário com o email "email18@email.com" não está logado
    When É tentado cadastrar um novo exame de fezes
    Then O exame não é cadastrado por o usuário não estar logado

  Scenario: A listagem dos exames funciona corretamente
    Given Existe um usuário com o email "email19@email.com" cadastrado
    And O usuário com o email "email19@email.com" está logado
    When É tentado listar todos os exames
    Then A listagem funciona

  Scenario: A listagem dos exames não funciona corretamente
    Given Existe um usuário com o email "email20@email.com" cadastrado
    And O usuário com o email "email20@email.com" não está logado
    When É tentado listar todos os exames
    Then A listagem não funciona por usuário não estar logado

  Scenario: A visualização de um exame funciona corretamente
    Given Existe um usuário com o email "email21@email.com" cadastrado
    And O usuário com o email "email21@email.com" está logado
    And Existe um exame cadastrado
    When É tentado visualizar um exame com id "2"
    Then A visualização funciona

  Scenario: A visualização de um não exame funciona corretamente devido ao exame não existir
    Given Existe um usuário com o email "email22@email.com" cadastrado
    And O usuário com o email "email22@email.com" está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona

  Scenario: A visualização de um não exame funciona corretamente devido usuário não estar logado
    Given Existe um usuário com o email "email23@email.com" cadastrado
    And O usuário com o email "email23@email.com" não está logado
    When É tentado visualizar um exame com id "4"
    Then A visualização não funciona devido ao usuário não estar logado