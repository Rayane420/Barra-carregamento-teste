<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Capturar Excecoes</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>Capturar Exceções com jQuery</h3>
	<input type="text" value="valor informado" id="txtvalor">
	<input type="button" onclick="testarExcecao();" value="Testar Excecao">
</body>

<script type="text/javascript">
	function testarExcecao() {
		var valorInformado = $('#txtvalor').val();
		
		$.ajax({
		method: "POST",
		url: "capturarExcecao",
		data: { valorParam: valorInformado}	
		})
		.done(function(response) {
			alert("Sucesso" + response)		
		})
		.fail(function(response) {
			alert("Erro:: " + response)		
		});
		
		
		
	}
</script>

</html>