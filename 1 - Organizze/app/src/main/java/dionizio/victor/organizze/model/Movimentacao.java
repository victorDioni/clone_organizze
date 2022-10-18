package dionizio.victor.organizze.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import dionizio.victor.organizze.config.ConfiguracaoFirebase;
import dionizio.victor.organizze.helper.Base64Custom;
import dionizio.victor.organizze.helper.DateCustom;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private double valor;
    private String key;

    public Movimentacao() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void salvarMovimentacao(String dataEscolhida){

        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();

        String mesAno = DateCustom.mesAnoDataEscolhida(dataEscolhida);

        //Recuperando o id do Usuario nesse caso o email
        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());

        DatabaseReference firebaseDatabase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebaseDatabase.child("movimentacao")
                        .child(idUsuario)
                        .child(mesAno)
                        .push() // Isso faz com que crie o idUnico no Firebase
                        .setValue(this); // Salva todas as propriedades da classe data, categoria etc
    }
}
