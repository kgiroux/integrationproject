<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">
         <meta charset="utf-8">
        <title>WebSocket!</title>
    </head>
    <body>
       
        <script  type="text/javascript">
            
            var wsUri = "ws://localhost:8080/quiz/echo";
            
            function init() {
            	 websocket = new WebSocket(wsUri);
                
                
            	document.getElementById("output").innerHTML = "init ok!";
                
                
                websocket.onopen = function(evt) {
                    onOpen(evt)
                };
                websocket.onmessage = function(evt) {
                    onMessage(evt)
                };
                websocket.onerror = function(evt) {
                    onError(evt)
                };
            }
           
            function onOpen(evt) {
                writeToScreen("Connected to Endpoint!");
               
            }
            function onMessage(evt) {
                
            	if(evt.data.startsWith("nb_connectes="))
            	{
            		//on a recu un message du type : nb_connectes=15
            	document.getElementById("nb_connectes").innerHTML = evt.data.substr(13);  //on enleve nb_connectes=
            	}
            	else{
            	//si message recu alors c'est une demande de refresh de la page
            	
            	writeToScreen("Message Received: " + evt.data);
            	}
                
                
            }
            function onError(evt) {
                writeToScreen('ERROR: ' + evt.data);
            }
            function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
                //websocket.close();
            }
            //ecrit une info dans le composant d'id : id
            function writeToScreen(message,id) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                 
                output.appendChild(pre);
            }
            window.addEventListener("load", init, false);
        </script>
        
        <div style="background-color: #444444">
        
            <form action="">
                <input onclick=" doSend(textID.value);" value="Send" type="button">
                <input id="textID" name="message" value="Hello WebSocket!" type="text"><br>
            </form>
        </div>
        <div id="nb_connectes"></div>
        <div id="output"></div>
        
</body>
</html>