package smclb.com.brunopera.smartclinica_beta.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private static FirebaseAuth autenticacao;

    //Retornar a instancia do FirebaseAuth
    public static FirebaseAuth getFirebaseAutenticacao(){

        if(autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }


}
