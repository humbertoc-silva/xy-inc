CREATE TABLE poi (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Identificador do ponto de interesse.',
  nome VARCHAR(60) NOT NULL COMMENT 'Nome do ponto de interesse.',
  coordenada_x INT NOT NULL COMMENT 'Coordenada X do ponto de interesse.',
  coordenada_y INT NOT NULL COMMENT 'Coordenada Y do ponto de interesse.',
  PRIMARY KEY (id))
COMMENT = 'Tabela que armazena os pontos de interesse.';