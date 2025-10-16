CREATE DATABASE BDINFORFLASH;
USE BDINFORFLASH;

/*CRIANDO AS TABELAS*/

CREATE TABLE LOGIN(
USUARIO VARCHAR(20),
SENHA VARCHAR(30)
);

CREATE TABLE TBCLIENTE(
IDCLIENTE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMECLIENTE  VARCHAR(20) NOT NULL,
CELCLIENTE  VARCHAR(15) NOT NULL,
CPFCLIENTE VARCHAR(14) NOT NULL,
EMAILCLIENTE VARCHAR(50)
);


CREATE TABLE TBFUNCIONARIO(
IDFUNCIONARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMEFUNC VARCHAR(20) NOT NULL,
CELFUNC VARCHAR(15) NOT NULL,
CPFFUNC VARCHAR(14) NOT NULL,
EMAILFUNC VARCHAR(50)
);

CREATE TABLE TBPEDIDO(
IDPEDIDO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
CODFUNCIONARIO INT NOT NULL,
CODCLIENTE INT NOT NULL,
HORAPED TIME,
DATAPED DATE,
STATUSPED VARCHAR(20) NOT NULL,
FOREIGN KEY (CODFUNCIONARIO) REFERENCES TBFUNCIONARIO (IDFUNCIONARIO),
FOREIGN KEY (CODCLIENTE) REFERENCES TBCLIENTE (IDCLIENTE)
)ENGINE=INNODB;


CREATE TABLE TBSERVICO(
IDSERVICO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
VALORSERV DECIMAL(5,2) NOT NULL ,
TIPOSERV VARCHAR(30) NOT NULL
);


CREATE TABLE TBATENDIMENTO(
IDATEND INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
CODSERVICO INT NOT NULL,
CODPEDIDO INT NOT NULL, 
OBSATEND VARCHAR(50),
QTDPROD INT NOT NULL,
FOREIGN KEY (CODSERVICO) REFERENCES TBSERVICO (IDSERVICO),
FOREIGN KEY (CODPEDIDO) REFERENCES TBPEDIDO (IDPEDIDO)
)ENGINE=INNODB;

/*INSERINDO DADOS*/

/*TBLOGIN*/
INSERT INTO LOGIN(USUARIO, SENHA)
VALUES ('João Lucas','22062009'),
		 ('Caio Henrique','20112008');
		 
		 
/*TBCLIENTE*/
INSERT INTO TBCLIENTE(NOMECLIENTE, CELCLIENTE, CPFCLIENTE, EMAILCLIENTE)
VALUES 
("Edson","11981704990","26767855839","edson-galvao75@gmail.com"),	
("Lara","16988459596","34214887644","lara_tereza_baptista@gmail.com"),
("Renata","11995651112","14497514820","renatateresinhacortereal@outlook.com.br"),
("Giovana","17993353350","15948700801","giovana_oliveira@gmail.com"),
("Sykes","9225107257","82522537849","sykes_03@gmail.com"),
("Luciana","13986323042","48178943859","luciana-bernardes99@gmail.com"),
("Arthur","11988259668","44894456877","arthur_erick_almeida@outlook.com.br"),
("Tails","17988259668","93873623266","tails_026@gmail.com"),
("Bento","12996746981","30432875840","bento_benjamin_monteiro@gmail.com"),
("Láis","11986807027","56997884849","laisfranciscapires@outlook.com.br"),
("Marcos","11988776655","12345678900","marcos.silva@hotmail.com"),
("Juliana","21999887766","23456789011","juliana.rodrigues@gmail.com"),
("Ricardo","31977665544","34567890122","ricardo.oliveira@outlook.com"),
("Fernanda","41988554433","45678901233","fernanda.lima@yahoo.com"),
("Carlos","11999443322","56789012344","carlos.santos@gmail.com");

	    
/*TBFUNCIONARIO*/	    
INSERT INTO TBFUNCIONARIO(NOMEFUNC, CELFUNC, CPFFUNC, EMAILFUNC)
VALUES ("Louise", "(11) 98102-2175", "651.056.738-59", "louise-dacunha76@gmail.com"),	
	    ("Giovana", "(11) 98794-7639", "016.620.298-37", "giovana_viana@gmail.com"),
	    ("Filipe", "(19) 99905-4904", "820.694.938-06", "filipeandersonbarros@outlook.com.br"),
	    ("Breno", "(11) 99585-4203", "178.617.588-69", "breno_victor_dacruz@gmail.com"),
	    ("Vitória", "(11) 99648-0796", "137.566.838-26", "vitoria_souza@gmail.com"),
	    ("Pedro", "(12) 99718-6223", "235.941.598-09", "pedroviniciusfigueiredo@outlook.com.br"),
	    ("Mário", "(14) 99973-8851", "137.969.068-42", "mario-moreira80@gmail.com"),
	    ("Benedita", "(11) 99215-2591", "156.382.168-04", "beneditaraquelsilva@gmail.com"),
	    ("Isabelle", "(15) 99992-8256", "024.578.628-76", "isabelleraimundadamata@gmail.com"),
	    ("Francisco", "(11) 99352-5020", "531.315.218-13", "francisco_novaes@outlook.com.br");
	    
/*TBPEDIDO*/

/*
Tipos de Status:

Em Preparação
Aguardando Coleta
Concluido
Cancelado
*/
INSERT INTO TBPEDIDO(CODFUNCIONARIO, CODCLIENTE, HORAPED, DATAPED, STATUSPED)
VALUES (1, 2, "17:02:02", "2025/09/15", "Concluido"),	
	    (1, 3, "12:02:13", "2025/09/03", "Concluido"),
	    (4, 1, "14:05:26", "2025/09/16", "Cancelado"),
	    (7, 8, "03:01:54", "2025/09/02", "Em preparação"),
	    (5, 6, "10:42:53", "2025/09/15", "Cancelado"),
	    (9, 1, "20:27:44", "2025/09/08", "Aguardando Coleta"),
	    (2, 4, "04:09:04", "2025/09/18", "Aguardando Coleta"),
	    (7, 9, "08:33:29", "2025/09/05", "Em preparação"),
	    (1, 2, "00:50:32", "2025/09/02", "Concluido"),
	    (1, 2, "18:24:53", "2025/09/01", "Cancelado");
	    
/*TBSERVICO*/
INSERT INTO TBSERVICO(VALORSERV, TIPOSERV)
VALUES (28.00,"Documento de carro"),	
	    (150.00,"Formatação de Computador"),
	    (25.00,"Topo de Bolo"),
	    (20.00,"Adesivos"),
	    (12.00,"Curriculo"),
	    (17.00,"Foto 3x4"),
	    (100.00,"Banner"),
	    (6.00,"Pasta de Música"),
	    (1.00,"Xerox"),
	    (6.00,"Boleto");
	    
/*TBATENDIMENTO*/
INSERT INTO TBATENDIMENTO(CODSERVICO,CODPEDIDO, OBSATEND,QTDPROD)
VALUES (2,1,"Formatação e Backup", 1),	
	    (1,2,"",1),
	    (4,3,"Adesivos de nome", 16),
	    (3,4,"19 anos, tema de carros", 1),
	    (6,5,"Fotos",4),
	    (5,6,"",1),
	    (7,7,"Banners para restaurante",2),
	    (8,8,"5 pastas de músicas",5),
	    (9,9,"",10),
	    (10,10,"Impressão de boletos cancelada",2);
	    
	    
	    /*AVALIAÇÃO*/
	    
/*1 - Consulte o nome, cpf, e quantidade de pedidos realizados, pelo funcionario com ID=1*/
SELECT F.NOMEFUNC AS 'NOME', F.CPFFUNC AS 'CPF', COUNT(*) AS 'QUANTIDADE DE PEDIDOS'
FROM tbfuncionario F, TBPEDIDO P
WHERE F.IDFUNCIONARIO = P.CODFUNCIONARIO 
AND P.CODFUNCIONARIO = 1;

/*2 - Consulte a observação e a data do pedido realizados depois do dia 03/09/2025, e não mostre os que a observação é nula*/
SELECT A.OBSATEND AS 'OBSERVAÇÃO', P.DATAPED AS 'DATA DO PEDIDO'
FROM tbatendimento A, tbpedido P
WHERE P.IDPEDIDO = A.CODPEDIDO AND P.DATAPED > "2025/09/03" AND A.OBSATEND <> "";

/*3 - Consulte o nome do funcionario, e o nome do cliente dos pedidos entre as datas de 05/09/2025 e 16/09/2025*/
SELECT F.NOMEFUNC AS 'NOME FUNC', C.NOMECLIENTE AS 'CLIENTE', P.DATAPED AS 'DATA'
FROM tbpedido P
INNER JOIN tbcliente C  ON C.IDCLIENTE = P.CODCLIENTE
INNER JOIN tbfuncionario F  ON F.IDFUNCIONARIO = P.CODFUNCIONARIO
WHERE P.DATAPED BETWEEN "2025/09/05" AND "2025/09/16";

/*4 - Consulte a quantidade de pedidos feitos em cada data*/
SELECT COUNT(*) AS 'QUANT VENDIDA', P.DATAPED
FROM tbpedido P, TBATENDIMENTO A
WHERE P.IDPEDIDO=A.CODPEDIDO
GROUP BY P.DATAPED;

/*5 - Consulte o nome do cliente, telefone, status do pedido, e a data, 
dos status que estão "cancelados", para descobrir o motivo do cancelamento*/
SELECT P.STATUSPED AS 'STATUS', P.DATAPED AS 'DATA', C.NOMECLIENTE AS 'CLIENTE', 
C.CELCLIENTE AS 'TELEFONE'
FROM tbpedido P, tbcliente C
WHERE C.IDCLIENTE = P.CODCLIENTE AND P.STATUSPED LIKE ('CANCELADO');



/*RELATÓRIOS*/

/*1- MESMO QUE A QUESTAO 5 DA PROVA*/

/*2- Consulte nome do funcionario, CPF e a quantidade de pedidos que ele concluiu*/
SELECT F.NOMEFUNC AS "FUNCIONÁRIO", F.CPFFUNC AS "CPF", COUNT(*) AS "QUANTIDADE DE PEDIDOS" 
FROM TBFUNCIONARIO F, TBPEDIDO P
WHERE F.IDFUNCIONARIO = P.CODFUNCIONARIO AND P.STATUSPED LIKE ('CONCLUIDO')
GROUP BY P.CODFUNCIONARIO;

/*3- Consulte a quantidade de pedidos feita em cada dia*/ 
SELECT COUNT(*) AS 'QTD DE PEDIDOS', P.DATAPED AS 'DATA'
FROM tbpedido P, tbatendimento A
WHERE P.IDPEDIDO = A.CODPEDIDO AND P.STATUSPED LIKE ('CONCLUIDO')
GROUP BY P.DATAPED;

/*4- Consulte o nome e telefone celular dos clientes com o pedido aguardando a coleta.*/
 SELECT C.NOMECLIENTE AS 'CLIENTE', C.CELCLIENTE AS 'NÚMERO'
 FROM tbpedido P, tbcliente C
 WHERE C.IDCLIENTE = P.CODCLIENTE AND P.STATUSPED LIKE ('AGUARDANDO COLETA');
 
 /*5- */
 SELECT P.IDPEDIDO, (S.VALORSERV * A.QTDPROD) AS 'PRECO TOTAL', P.DATAPED AS 'DATA'
 FROM tbpedido P, tbatendimento A, tbservico S

 WHERE P.IDPEDIDO = A.CODPEDIDO AND S.IDSERVICO = A.CODSERVICO;
