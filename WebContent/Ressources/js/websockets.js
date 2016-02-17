  
            
            function init() {
            	 websocket = new WebSocket(wsUri);
                /* websocket.onopen = function(evt) {onOpen(evt)};*/
                websocket.onmessage = function(evt) {onMessage(evt)};
                websocket.onerror = function(evt) {onError(evt)};
            }
           
            //si on recoit un message
            function onMessage(evt) {
                
            	if(evt.data.startsWith("nb_connectes="))
            	{
            		//on a recu un message du type : nb_connectes=15
            	document.getElementById("messageWebSockets").innerHTML = evt.data.substr(13)+ ' joueur(s) connecté(s)';  //on enleve nb_connectes=
            	}
            	else if(evt.data.startsWith("rafraichir="))
            	{
            		//on a recu le message : rafraichir=
            		//on force donc le refresh de la page
            	window.location='VueQuestion.do';
            	}
            	else if(evt.data.startsWith("prevenirQuizLance"))
            	{
            		//on a recu le message : prevenirQuizLance
            		//on fait un refresh de la page seulement si le joueur se trouve sur la page liste des quiz
            	if(window.location.href.endsWith("VueListeQuiz.do")  )
            		window.location='VueListeQuiz.do';
            	
            	}
            	else{
            	//si message recu alors c'est une demande de refresh de la page
            	
            	writeToScreen("Message Received: " + evt.data);
            	}
                
                
            }
            function onError(evt) {
            	document.getElementById("nb_connectes").innerHTML="ERREUR websockets"
            }
            /*function doSend(message) {
                writeToScreen("Message Sent: " + message);
                websocket.send(message);
                //websocket.close();
            }*/