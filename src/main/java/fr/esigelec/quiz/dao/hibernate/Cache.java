package fr.esigelec.quiz.dao.hibernate;

import java.util.List;

import fr.esigelec.quiz.dto.Quiz;


//cache de la BDD pour eviter de trop nombreuses requetes en BDD et donc ameliorer les temps de reponse
public class Cache {
	
	
	
		//cache contenant la liste des quiz de la BDD, les quiz sont associés à des questions et propositions
		private static List<Quiz> cacheListeQuiz=null;

		public static List<Quiz> getCacheListeQuiz() {
			return cacheListeQuiz;
		}

		public static void setCacheListeQuiz(List<Quiz> cacheListeQuiz) {
			Cache.cacheListeQuiz = cacheListeQuiz;
		}
		
		public static void viderCacheListeQuiz(){
			
			//on vide le cache pour forcer son rechargement
			cacheListeQuiz=null;
			
		}
		
		
		
		
		
		
	

}
