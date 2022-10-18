package dionizio.victor.organizze.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static FirebaseAuth autenticacao;
    private static DatabaseReference firebase;

    //retorna a instancia do FirebaseDatabse que nos permite salvar os dados no BD
    public static DatabaseReference getFirebaseDatabase(){
        if (firebase == null){
            firebase = FirebaseDatabase.getInstance().getReference();
        }
        return firebase;
    }

    // Retorna a instância do FirebaseAuth
    // Metedos/atributos static nao precisa ser instanciado em outra classe para ser usado
    public static FirebaseAuth getFirebaseAutenticacao(){
        if(autenticacao == null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
