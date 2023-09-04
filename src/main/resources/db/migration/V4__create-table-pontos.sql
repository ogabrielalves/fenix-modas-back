CREATE TABLE pontos (
  id SERIAL PRIMARY KEY,
  id_funcionario INT NOT NULL,
  tipo_registro VARCHAR(255) NOT NULL,
  horario_ponto TIMESTAMP NOT NULL,
  FOREIGN KEY (id_funcionario) REFERENCES funcionarios (id)
);