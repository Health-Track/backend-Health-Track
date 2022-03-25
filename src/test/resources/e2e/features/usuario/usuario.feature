Feature: Verificando se as funcionalidades de usuário funcionam corretamente

  Scenario: O cadastro de um usuário funciona corretamente
    Given Não existe um usuário com o email "email1@email.com" cadastrado
    When É tentado cadastrar um novo usuário com o email "email1@email.com"
    Then O cadastro do usuário é realizado com sucesso

  Scenario: O cadastro de um usuário falha devido ao email já estar cadastrado
    Given Existe um usuário com o email "email2@email.com" cadastrado
    When É tentado cadastrar um novo usuário com o email "email2@email.com"
    Then O cadastro do ussuário falha

  Scenario: A mudança da senha do usuario funciona corretamente
    Given Existe um usuário com o email "email3@email.com" cadastrado
    And O usuário com o email "email3@email.com" está logado
    When É tentado alterar a senha para uma nova senha válida
    Then A senha é alterada corretamente

  Scenario: A mudança da senha do usuario falha devido a senha informada estar errada
    Given Existe um usuário com o email "email4@email.com" cadastrado
    And O usuário com o email "email4@email.com" está logado
    When É tentado alterar a senha para uma nova senha válida utilizando a senha antiga errada
    Then A senha não é alterada

  Scenario: A mudança da senha do usuario falha devido ao usuário não estar logado
    Given Existe um usuário com o email "email5@email.com" cadastrado
    And O usuário não esta logado
    When É tentado alterar a senha para uma nova senha válida utilizando a senha antiga errada
    Then Falha por o usuario não estar logado

  Scenario: A remoção de um usuario falha devido ao usuario não estar logado
    Given Existe um usuário com o email "email6@email.com" cadastrado
    And O usuário não esta logado
    When É tentado remover o usuario
    Then Falha por o usuario não estar logado

  Scenario: A remoção de um usuario funciona corretamente
    Given Existe um usuário com o email "email7@email.com" cadastrado
    And O usuário com o email "email7@email.com" está logado
    When É tentado remover o usuario
    Then O usuário é removido