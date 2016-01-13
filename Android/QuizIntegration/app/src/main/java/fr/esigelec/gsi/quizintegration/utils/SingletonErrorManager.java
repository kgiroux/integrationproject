package fr.esigelec.gsi.quizintegration.utils;

import fr.esigelec.gsi.quizintegration.Objects.Personne;

/**
 * Created by Kevin-Giroux on 13/01/2016.
 */
public class SingletonErrorManager {

        private static volatile SingletonErrorManager instance;
        private ErrorManager error;

        private SingletonErrorManager(){
            super();
            error = new ErrorManager();
        }

        public ErrorManager getError ()
        {
            return error;
        }

        public static SingletonErrorManager getInstance(){
            if(SingletonErrorManager.instance == null){
                synchronized (SingletonPersonne.class){
                    if(SingletonErrorManager.instance == null){
                        SingletonErrorManager.instance = new SingletonErrorManager ();
                    }
                }
            }
            return SingletonErrorManager.instance;

        }
    }

