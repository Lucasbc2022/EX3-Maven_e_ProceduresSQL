<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/styles.css">
<title>Cliente</title>
</head>
<body>
     <div>
          <jsp:include page="menu.jsp"/>
     </div>
     <div align="center" class="container">
           <form action="cliente" method="post">
              <p class="title">
                  <b>Cliente</b>
              </p>
              <table>
                 
                <tr>
                   <td colspan="3">
                    <input class="id_input_data" type="text" 
                    id="cpf" name="cpf" placeholder="CPF"
                    value='<c:out value="${cliente.cpf }"></c:out>'>
                   </td>
                   <td><input type="submit" id="botao" name="botao" value="Buscar"></td>
                </tr> 
                <tr>
                   <td colspan="4">
                    <input class="input_data" type="text" 
                    id="nome" name="nome" placeholder="Nome"
                    value='<c:out value="${cliente.nome }"></c:out>'>
                   </td>
                </tr> 
                <tr>
                   <td colspan="4">
                    <input class="input_data" type="text" 
                    id="email" name="email" placeholder="E-mail"
                    value='<c:out value="${cliente.email }"></c:out>'>
                   </td>
                </tr>  
                <tr>
                   <td colspan="4">
                    <input class="input_data" type="number" min="0" step="0.01"
                    id="limite_credito" name="limite_credito" placeholder="Limite de credito"
                    value='<c:out value="${cliente.limite_cartao }"></c:out>'>
                   </td>
                </tr>
                <tr>
                   <td colspan="4">
                    <input class="input_data" type="text" 
                    id="dt_nasci" name="dt_nasci" placeholder="Data de Nascimento"
                    value='<c:out value="${cliente.dt_nasci }"></c:out>'>
                   </td>
                </tr>  
                <tr>
                   <td><input type="submit" id="botao" name="botao" value="Inserir"></td>
                   <td><input type="submit" id="botao" name="botao" value="Atualizar"></td>
                   <td><input type="submit" id="botao" name="botao" value="Excluir"></td>
                   <td><input type="submit" id="botao" name="botao" value="Listar"></td>
                </tr>
                                                                    
              </table>   
           </form>
    </div>
    
    <br />
	<div align="center">
		<c:if test="${not empty erro }">
			<H2><c:out value="${erro }" /></H2>
		</c:if>
		<c:if test="${not empty saida }">
			<H2><c:out value="${saida }" /></H2>
		</c:if>
	</div>
	<div align="center">
		<c:if test="${not empty clientes }">
			<table border="1">
				<thead>
					<tr>
						<th>CPF</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Limite de Credito</th>
						<th>Data de nascimento</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${clientes }" var="c">
						<tr>
							<td><c:out value="${c.cpf }"></c:out></td>
							<td><c:out value="${c.nome }"></c:out></td>
							<td><c:out value="${c.email }"></c:out></td>
							<td><c:out value="${c.limite_cartao }"></c:out></td>
							<td><c:out value="${c.dt_nasci }"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>	
	</div>
      
</body>
</html>