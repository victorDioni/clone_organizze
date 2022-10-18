package dionizio.victor.organizze.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import dionizio.victor.organizze.config.ConfiguracaoFirebase;

public class Usuario {

    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Double receitaTotal = 0.00;
    private Double despesaTotal = 0.00;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    // @Exclude - Essa anotacao exclui a propriedado do firebase
    // Remove os dados na hora de salvar o objeto no BD

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public void salvar(){
        DatabaseReference firebaseDatabase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebaseDatabase.child("usuarios")
                        .child(this.idUsuario)
                        .setValue(this); // salvando o objeto usuario


    }
}
