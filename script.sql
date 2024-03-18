CREATE SEQUENCE IF NOT EXISTS seq_cliente_id
START WITH 1
INCREMENT BY 1
MINVALUE 1
NO MAXVALUE
NO CYCLE;

CREATE TABLE IF NOT EXISTS cliente (
    id INT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('seq_cliente_id'),
    limite INT NOT NULL,
    saldo INT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_transacao_id
START WITH 1
INCREMENT BY 50
MINVALUE 1
NO MAXVALUE
NO CYCLE;

CREATE TABLE IF NOT EXISTS transacao (
    id INT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('seq_transacao_id'),
    valor INT NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    descricao VARCHAR(10) NOT NULL,
    realizada_em TIMESTAMP WITH TIME ZONE NOT NULL,
    id_cliente INT
);

ALTER TABLE if exists transacao
DROP CONSTRAINT IF EXISTS fk_id_client;

ALTER TABLE if exists transacao
ADD CONSTRAINT fk_id_client FOREIGN KEY (id_cliente)
REFERENCES cliente;

CREATE index IF not exists idx_cliente_order_by_realizado_em_desc 
ON transacao (id_cliente, realizada_em DESC);

INSERT INTO cliente(id,limite,saldo) VALUES
    (1,100000,0),
    (2,80000,0),
    (3,1000000,0),
    (4,10000000,0),
    (5,500000,0)
ON CONFLICT DO NOTHING;
