package dionizio.victor.organizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import dionizio.victor.organizze.R;
import dionizio.victor.organizze.config.ConfiguracaoFirebase;
import dionizio.victor.organizze.helper.Base64Custom;
import dionizio.victor.organizze.helper.DateCustom;
import dionizio.victor.organizze.model.Movimentacao;
import dionizio.victor.organizze.model.Usuario;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas);

        campoValor  = findViewById(R.id.editValorReceita);
        campoData  = findViewById(R.id.editData);
        campoCategoria  = findViewById(R.id.editCategoria);
        campoDescricao  = findViewById(R.id.editDescricao);

        //Preenche o campo data com a data atual
        campoData.setText(DateCustom.dataAtual());

        recuperarDespesaTotal();


    }



    public void salvarDespesa(View view){

        if(validarCamposDespesa()){
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());

            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setData(data);
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setTipo("d");

            Double despesaAtualizada = despesaTotal + valorRecuperado;

            atualizarDespesa(despesaAtualizada);

            movimentacao.salvarMovimentacao(data);

            finish();
        }
    }

    public Boolean validarCamposDespesa(){
        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if(!textoValor.isEmpty()){
            if(!textoData.isEmpty()){
                if(!textoCategoria.isEmpty()){
                    if(!textoDescricao.isEmpty()){
                        return true;
                    }else{
                        Toast.makeText(DespesasActivity.this,
                                "Descrição não foi preenchido!",
                                Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(DespesasActivity.this,
                            "Categoria não foi preenchido!",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(DespesasActivity.this,
                        "Data não foi preenchido!",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(DespesasActivity.this,
                    "Valor não foi preenchido!",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void recuperarDespesaTotal(){

        // Recuperando o id do usuario
        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        // Criando ouvinte
        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Recuperando dados

                /* Ao passar uma classe como parametro no getValue
                 ele vai converter o retorno do firebase em um objeto da classe passada
                 */

                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void atualizarDespesa(Double despesa){
        // Recuperando o id do usuario
        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesa);
    }
}