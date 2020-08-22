<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barra de Progresso</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">  
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
/*Cor do fundo da barra de progresso*/
#myProgress {
	width: 100%;
	background-color: #ddd;
}

/*Cor da barra de progresso*/
#myBar {
	width: 1%;
	height: 30px;
	background-color: #00FA9A	
}

.ui-progressbar {
	position: : relative;
}

.progress-label{
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0 #fff;
	
}
</style>

</head>
<body>
	<br/>
	<center><h1>Exemplo com JavaScript</h1></center>

	<div id="myProgress">
		<div id="myBar">
		</div>
	</div>
	<br/>
	<button onclick="exibirBarra()">Iniciar carregamento</button>
	
	<br/>
	<br/>
	
	<center><h1>Exemplo com jQuery</h1></center>
	<div id="progressbar">
		<div class="progress-label">Carregando...</div>
	</div>
	
	<script type="text/javascript">
	//script barra de progresso com Jquery
	$(function() {
		var progressbar = $("#progressbar"), progresslabel = $(".progress-label");
		
		progressbar.progressbar ({
			value : false,
			change : function (){
				progresslabel.text (progressbar.progressbar('value') + "%" );
			},
			complete : function() {
				progresslabel.text('Completo');
			}
		});
		
		
		function progress(){
			var val = progressbar.progressbar ("value") || 0;
			
			progressbar.progressbar ("value", val + 2);
			
			if(val < 99){
				setTimeout(progress, 80);
			}
		}
		
		setTimeout(progress, 2000);
	});
	
	
	//script barra de progresso com JS
	function exibirBarra(){
		
		var elem = document.getElementById("myBar"); //pegando a barra de progresso
		var width = 1;
		var id = setInterval(frame, 10); //tempo para a barra ser completamente carregada
		
		
		function frame() {
			if(width >= 100){ //se for maior que 100% vai parar o crescimento da barra de progresso
				clearInterval(id);
			}else{
				width++;
				elem.style.width = width + "%";
			}
				
		}
		
		
	}
	
	</script>
</body>
</html>