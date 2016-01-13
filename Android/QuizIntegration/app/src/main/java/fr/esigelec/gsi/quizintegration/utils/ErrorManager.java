package fr.esigelec.gsi.quizintegration.utils;

/**
 * Created by Kevin-Giroux on 13/01/2016.
 */
public class ErrorManager {

    public enum ERROR{
        DO_GET_FORBIDDEN,
        USER_NOT_FOUND,
        PASS_INCORRECT,
        MISSING_ARG,
        NOTHING
    }


    public ErrorManager(){

    }

    private ERROR getError(int errorCode){
        switch(errorCode){
            case 1 :
                return ERROR.DO_GET_FORBIDDEN;
            case 2 :
                return ERROR.USER_NOT_FOUND;
            case 3 :
                return ERROR.PASS_INCORRECT;
            case 4 :
                return ERROR.MISSING_ARG;
            default :
                return ERROR.NOTHING;
        }
    }

    public String errorManager(int errorCode){
        ERROR error = getError(errorCode);
        switch (error){
            case DO_GET_FORBIDDEN :
                return "GET METHOD NOT ALLOWED";
            case USER_NOT_FOUND :
                return "USER NOT FOUND";
            case PASS_INCORRECT :
                return "PASSWORD INCORRECT";
            case MISSING_ARG :
                return "MISSING ARG";
            default :
                return "NOTHING";
        }
    }
}
