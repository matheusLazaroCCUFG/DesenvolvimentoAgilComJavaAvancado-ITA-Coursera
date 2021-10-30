CREATE TABLE usuario
(
    login text NOT NULL,
    email text,
    nome text,
    senha text,
    pontos integer,
    CONSTRAINT usuario_pkey PRIMARY KEY (login)
);

CREATE SEQUENCE topico_id_topico_seq
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE topico
(
    id_topico integer NOT NULL DEFAULT nextval('topico_id_topico_seq'::regclass),
    titulo text,
    conteudo text,
    login text,
    CONSTRAINT topico_pkey PRIMARY KEY (id_topico),
    CONSTRAINT topico_login_fkey FOREIGN KEY (login)
        REFERENCES usuario (login) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE comentario_id_comentario_seq
    INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE comentario
(
    id_comentario integer NOT NULL DEFAULT nextval('comentario_id_comentario_seq'::regclass),
    comentario text,
    login text,
    id_topico integer,
    CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario),
    CONSTRAINT comentario_id_topico_fkey FOREIGN KEY (id_topico)
        REFERENCES topico (id_topico) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT comentario_login_fkey FOREIGN KEY (login)
        REFERENCES usuario (login) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
)