# SoftPlayer

## Tecnologias utilizadas
### Backend
 - Java;
 - Spring;
 - Spock Framework;

Obs.: A escolha pelo Spock Framework para escrita de testes automatizados foi devido a eu estar estudando e o mesmo possuir uma escrita de testes mais clean, deixando mais friendly e conseguindo mais produtividade.

# Exercício 1

## Classes  
   ### Services
   - MessageService     -> Classe contendo os métodos para escrita da mensagem de retorno
   - CodeService        -> Classe responsável por retornar
   - ObservacaoService  -> Classe core da aplicação onde terá a regra do domain e será responsável pela primeira validação para apartir disso direcionar a para os próximos passos
  
   ### Utils
   - ConstantUtils      -> Classe reunindo as constantes utilizadas no projeto
   - MessageUtils       -> Classe contendo utilitário para geração da mensagem

# Exercício 2

## Classes
  ### Models
   - Composicao         -> Classe representante do json de entrada
   - ComposicaoRetorno  -> Classe representante da mensagem de retorno da aplicação
   Obs.: Decidi separar para melhor manutenção futura e deixar cada modelo com seu respectivo objetivo.
   
   ### Services
   - ComposicaoService -> Classe core da aplicação onde terá toda regra do domain
   
   ### Repositories
   - ComposiçãoRepository -> Interface usada para manipulação da classe Composição no banco de dados
   
   ### Utils
   - ConstantUtils      -> Classe reunindo as constantes utilizadas no projeto
   - MessageUtils       -> Classe utilitária para conversão de elemenos ligados às mensagens de entrada (Composicao) e de saída (ComposicaoRetorno)
   
   ### Exceptions
   - MessageConverterException -> Classe de Exception para ser utilizada caso ocorra alguma exception relacionada à conversão da mensagem


Obs.: O arquivo .json contendo os valores a serem lidos está na seguinte path: "src/main/resources/dados-entrada-servicos-composicoes.json"
