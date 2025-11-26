CREATE DATABASE BDINFORFLASH;
USE BDINFORFLASH;

/*CRIANDO AS TABELAS*/

CREATE TABLE LOGIN(
USUARIO VARCHAR(20) PRIMARY KEY,
SENHA VARCHAR(30),
STATUSLOGIN INT NOT NULL
);

CREATE TABLE TBCLIENTE(
IDCLIENTE INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
NOMECLIENTE  VARCHAR(20) NOT NULL,
CELCLIENTE  VARCHAR(15) NOT NULL,
CPFCLIENTE VARCHAR(14) NOT NULL,
STATUSCLIENTE INT NOT NULL,
EMAILCLIENTE VARCHAR(50)
);


CREATE TABLE TBFUNCIONARIO(
IDFUNCIONARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
USUARIOFUNC VARCHAR(20) NOT NULL,
NOMEFUNC VARCHAR(20) NOT NULL,
CELFUNC VARCHAR(15) NOT NULL,
CPFFUNC VARCHAR(14) NOT NULL,
STATUSFUNC INT NOT NULL,
EMAILFUNC VARCHAR(50),
FOREIGN KEY (USUARIOFUNC) REFERENCES LOGIN (USUARIO)
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
TIPOSERV VARCHAR(30) NOT NULL,
STATUSERV INT NOT NULL
);


CREATE TABLE TBATENDIMENTO(
IDATEND INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
CODSERVICO INT NOT NULL,
CODPEDIDO INT NOT NULL, 
OBSATEND VARCHAR(50),
QTDPROD INT NOT NULL,
PRECOATEND DECIMAL(5,2),
FOREIGN KEY (CODSERVICO) REFERENCES TBSERVICO (IDSERVICO),
FOREIGN KEY (CODPEDIDO) REFERENCES TBPEDIDO (IDPEDIDO)
)ENGINE=INNODB;

/*INSERINDO DADOS*/

/*TBLOGIN*/
INSERT INTO LOGIN(USUARIO, SENHA, STATUSLOGIN)
VALUES /*('João','22062009', 1),
		 ('Caio','20112008', 1);*/
		 ('Louise', '123', 1),
		 ('Giovana', '123', 1),
		 ('Filipe', '123', 1),
		 ('Breno', '123', 1),
		 ('Vitoria', '123', 1),
		 ('Pedro', '123', 1),
		 ('Mario', '123', 1),
		 ('Benedita', '123', 1),
		 ('Isabelle', '123', 1),
		 ('Francisco', '123', 1);
		 
		 
/*TBCLIENTE*/
INSERT INTO TBCLIENTE(NOMECLIENTE, CELCLIENTE, CPFCLIENTE, EMAILCLIENTE, STATUSCLIENTE)
VALUES 
("Edson","(11) 98170-4990","267.678.558-39","edson-galvao75@gmail.com", 1),	
("Lara","(16) 98845-9596","342.148.876-44","lara_tereza_baptista@gmail.com", 1),
("Renata","(11) 99565-1112","144.975.148-20","renatateresinhacortereal@outlook.com.br", 1),
("Giovana","(17) 99335-3350","159.487.008-01","giovana_oliveira@gmail.com", 1),
("Sykes","(92) 25107-257","825.225.378-49","sykes_03@gmail.com", 1),
("Luciana","(13) 98632-3042","481.789.438-59","luciana-bernardes99@gmail.com", 1),
("Arthur","(11) 98825-9668","448.944.568-77","arthur_erick_almeida@outlook.com.br", 1),
("Tails","(17) 98825-9668","938.736.232-66","tails_026@gmail.com", 1),
("Bento","(12) 99674-6981","304.328.758-40","bento_benjamin_monteiro@gmail.com", 1),
("Láis","(11) 98680-7027","569.978.848-49","laisfranciscapires@outlook.com.br", 1),
("Marcos","(11) 98877-6655","123.456.789-00","marcos.silva@hotmail.com", 1),
("Juliana","(21) 99988-7766","234.567.890-11","juliana.rodrigues@gmail.com", 1),
("Ricardo","(31) 97766-5544","345.678.901-22","ricardo.oliveira@outlook.com", 1),
("Fernanda","(41) 98855-4433","456.789.012-33","fernanda.lima@yahoo.com", 1),
("Carlos","(11) 99944-3322","567.890.123-44","carlos.santos@gmail.com", 1);

	    
/*TBFUNCIONARIO*/	    
INSERT INTO TBFUNCIONARIO(NOMEFUNC, CELFUNC, CPFFUNC, EMAILFUNC, USUARIOFUNC, STATUSFUNC)
VALUES ("Louise", "(11) 98102-2175", "651.056.738-59", "louise-dacunha76@gmail.com", "Louise", 1),	
	    ("Giovana", "(11) 98794-7639", "016.620.298-37", "giovana_viana@gmail.com", "Giovana", 1),
	    ("Filipe", "(19) 99905-4904", "820.694.938-06", "filipeandersonbarros@outlook.com.br", "Filipe", 1),
	    ("Breno", "(11) 99585-4203", "178.617.588-69", "breno_victor_dacruz@gmail.com", "Breno", 1),
	    ("Vitória", "(11) 99648-0796", "137.566.838-26", "vitoria_souza@gmail.com", "Vitoria", 1),
	    ("Pedro", "(12) 99718-6223", "235.941.598-09", "pedroviniciusfigueiredo@outlook.com.br", "Pedro", 1),
	    ("Mário", "(14) 99973-8851", "137.969.068-42", "mario-moreira80@gmail.com", "Mario", 1),
	    ("Benedita", "(11) 99215-2591", "156.382.168-04", "beneditaraquelsilva@gmail.com", "Benedita", 1),
	    ("Isabelle", "(15) 99992-8256", "024.578.628-76", "isabelleraimundadamata@gmail.com", "Isabelle", 1),
	    ("Francisco", "(11) 99352-5020", "531.315.218-13", "francisco_novaes@outlook.com.br", "Francisco", 1);	    
	    
/*TBPEDIDO*/

/*
Tipos de Status:

Em Preparação
Aguardando Coleta
Concluido
Cancelado
*/
INSERT INTO TBPEDIDO(CODFUNCIONARIO, CODCLIENTE, HORAPED, DATAPED, STATUSPED)
VALUES (1, 2, "17:02:02", "2025/09/15", "Finalizado"),	
	    (1, 3, "12:02:13", "2025/09/03", "Finalizado"),
	    (4, 1, "14:05:26", "2025/09/16", "Cancelado"),
	    (7, 8, "03:01:54", "2025/09/02", "Em preparação"),
	    (5, 6, "10:42:53", "2025/09/15", "Cancelado"),
	    (9, 1, "20:27:44", "2025/09/08", "Aguardando Coleta"),
	    (2, 4, "04:09:04", "2025/09/18", "Aguardando Coleta"),
	    (7, 9, "08:33:29", "2025/09/05", "Em preparação"),
	    (1, 2, "00:50:32", "2025/09/02", "Finalizado"),
	    (1, 2, "18:24:53", "2025/09/01", "Finalizado");
	    
/*TBSERVICO*/
INSERT INTO TBSERVICO(VALORSERV, TIPOSERV, STATUSERV)
VALUES (28.00,"Documento de carro", 1),	
	    (150.00,"Formatação de Computador", 1),
	    (25.00,"Topo de Bolo", 1),
	    (20.00,"Adesivos", 1),
	    (12.00,"Curriculo", 1),
	    (17.00,"Foto 3x4", 1),
	    (100.00,"Banner", 1),
	    (6.00,"Pasta de Música", 1),
	    (1.00,"Xerox", 1),
	    (6.00,"Boleto", 1);
	    
/*TBATENDIMENTO*/
INSERT INTO TBATENDIMENTO(CODSERVICO,CODPEDIDO, OBSATEND,QTDPROD,PRECOATEND)
VALUES (2,1,"Formatação e Backup", 1, 150.00),	
	    (1,2,"",1, 28.00),
	    (4,3,"Adesivos de nome", 16, 320.00),
	    (3,4,"19 anos, tema de carros", 1, 25.00),
	    (6,5,"Fotos",4, 68.00),
	    (5,6,"",1, 12.00),
	    (7,7,"Banners para restaurante",2, 200.00),
	    (8,8,"5 pastas de músicas",5, 30.00),
	    (9,9,"",10, 10.00),
	    (10,10,"Impressão de boletos cancelada",2, 12.00);
	    
	    
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