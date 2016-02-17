package fr.esigelec.quiz.controleur;



import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/echo")
public class WebSocket {

	
	//liste des session des clients
	private static Set<Session> userSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	
	/**
     * Callback hook for Connection open events. This method will be invoked when a 
     * client requests for a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
    	System.out.println("open session "+userSession.getId());
    	
        userSessions.add(userSession);
        System.out.println("nb session = "+userSessions.size());
        miseAJourNbConnectes();
    }
     
    /**
     * Callback hook for Connection close events. This method will be invoked when a
     * client closes a WebSocket connection.
     * @param userSession the userSession which is opened.
     */
    @OnClose
    public void onClose(Session userSession) {
    	System.out.println("fermeture session "+userSession.getId());
        userSessions.remove(userSession);
        miseAJourNbConnectes();
    }
     
    /**
     * Callback hook for Message Events. This method will be invoked when a client
     * send a message.
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void onMessage(String message, Session userSession) {
        System.out.println("Message Received: " + message);
        for (Session session : userSessions) {
            System.out.println("Sending to " + session.getId());
            session.getAsyncRemote().sendText(message);
        }
    }
    
    /**
     * on met a jour le nb de connectes chez tous les clients connectes
     */
    public void miseAJourNbConnectes(){
    	
    	for (Session session : userSessions) {
          
            session.getAsyncRemote().sendText("nb_connectes="+userSessions.size());
        }
    }
    
    
    
    /**
     * on force le refresh de tous les clients connectes pour qu'ils puissent avoir la page question
     */
    public static  void rafraichirTousLesClients(){
    	System.out.println("websocket . rafraichirTousLesClients : "+userSessions.size());
    	for (Session session : userSessions) {
          
            session.getAsyncRemote().sendText("rafraichir=VueQuestion");
        }
    }
    
    
    /**
     * on previent les clients que l'animateur vient de lancer un quiz
     */
    public static  void prevenirQueQuizLance(){
    	System.out.println("websocket . prevenirQueQuizLance : "+userSessions.size());
    	for (Session session : userSessions) {
          
            session.getAsyncRemote().sendText("prevenirQuizLance");
        }
    }
    
    
    
   
	
	
  
}
