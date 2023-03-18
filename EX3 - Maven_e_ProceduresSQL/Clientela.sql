CREATE DATABASE Clientela
GO

USE Clientela 
GO

CREATE TABLE Cliente (
CPF                   CHAR(11)            NOT NULL,
Nome                  VARCHAR(100)        NOT NULL,
Email                 VARCHAR(100)        NOT NULL,
Limite_de_credito     DECIMAL(7, 2)       NOT NULL,
Dt_Nascimento         VARCHAR(10)                NOT NULL
PRIMARY KEY(CPF)
)
GO



CREATE PROCEDURE sp_cliente
	(@opcao CHAR(1),
	 @cpf CHAR(11), 
	 @nome VARCHAR(100), 
	 @email VARCHAR(100), 
	 @limite_credito DECIMAL(7, 2), 
	 @dt_Nascimento VARCHAR(10) ,
	 @saida VARCHAR(200) OUTPUT)
AS
	IF (UPPER(@opcao) = 'D' AND @cpf IS NOT NULL)
	BEGIN
		DELETE Cliente WHERE CPF = @cpf
		SET @saida = 'Cliente com CPF'+ @cpf +' excluído' 
	  END
	  ELSE
	    BEGIN
		IF (UPPER(@opcao) = 'D' AND @cpf IS NULL)
		BEGIN
			RAISERROR('Não é possível excluir sem o CPF', 16, 1)
		END
	IF (UPPER(@opcao) = 'I')
	BEGIN
		DECLARE @tam_cpf  VARCHAR(20)
		SET @tam_cpf = LEN(@cpf)
				IF(@tam_cpf != 11)
				BEGIN
				RAISERROR('CPF invalido', 16, 1)
	            END
				   IF(CAST(SUBSTRING(@cpf, 1, 3) AS INT) = (CAST(SUBSTRING(@cpf, 1, 1) AS INT) * 111) OR
	                  CAST(SUBSTRING(@cpf, 4, 3) AS INT) = (CAST(SUBSTRING(@cpf, 4, 1) AS INT) * 111) OR
	                  CAST(SUBSTRING(@cpf, 7, 3) AS INT) = (CAST(SUBSTRING(@cpf, 7, 1) AS INT) * 111) OR
	                  CAST(SUBSTRING(@cpf, 10, 3) AS INT) = (CAST(SUBSTRING(@cpf, 10, 1) AS INT) * 11)) 
	                  BEGIN
	                  RAISERROR('CPF invalido', 16, 1)
					  Print(CAST(SUBSTRING(@cpf, 1, 1) AS INT) * 111)
	                  END
					ELSE
					BEGIN
					INSERT INTO Cliente VALUES
					(@cpf, @nome, @email, @limite_credito, @dt_Nascimento)
					SET @saida = 'Cliente #'+@cpf+' inserido'
				END
    END
				ELSE
				BEGIN
					IF (UPPER(@opcao) = 'U')
					BEGIN
						UPDATE Cliente
						SET Nome = @nome, 
						    Email = @email,
							Limite_de_credito = @limite_credito, 
							Dt_Nascimento = @dt_Nascimento
						WHERE CPF = @cpf
						SET @saida = 'Cliente com '+@cpf+' atualizado'
					END
					ELSE
					BEGIN
						RAISERROR('Opção inválida', 16, 1)
				END
		END
	END

 SELECT * FROM CLIENTE