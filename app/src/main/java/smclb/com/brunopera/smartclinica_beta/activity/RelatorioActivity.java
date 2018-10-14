package smclb.com.brunopera.smartclinica_beta.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import smclb.com.brunopera.smartclinica_beta.R;
import smclb.com.brunopera.smartclinica_beta.config.ConfiguracaoFirebase;
import smclb.com.brunopera.smartclinica_beta.helper.Base64Custom;
import smclb.com.brunopera.smartclinica_beta.model.Prontuario;
import smclb.com.brunopera.smartclinica_beta.model.Telefone;
import smclb.com.brunopera.smartclinica_beta.model.Usuario;

public class RelatorioActivity extends AppCompatActivity {

    private TextView txtCabecalho;
    private TextView txtBody;

    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCabecalho = findViewById(R.id.txtCabecalho);
        txtBody = findViewById(R.id.txtBody);

        recuperarBody();
        recuperarCabecalho();
    }

    public void recuperarBody() {

        final String emailUsuario = autenticacao.getCurrentUser().getEmail();
        final String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        final DatabaseReference usuarioRef = firebaseRef.child("prontuario").child(idUsuario);




        /*usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                txtBody.setText(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                ArrayList<String> arrBody = new ArrayList<String>();
               for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Prontuario prontuario = snapshot.getValue(Prontuario.class);

                   arrBody.add(prontuario.getIdade());
                   arrBody.add(prontuario.getDataNascimento());
                   arrBody.add(prontuario.getEstadoCivil());
                   arrBody.add(prontuario.getEndereco());
                   arrBody.add(prontuario.getBairro());
                   arrBody.add(prontuario.getCidade());
                   arrBody.add(prontuario.getCEP());
                   arrBody.add(prontuario.getTelefone());
                   arrBody.add(prontuario.getCelular());
                   arrBody.add(prontuario.getProfissao());
                   arrBody.add(prontuario.getMedicamentos());
                   arrBody.add(prontuario.getOrteses());
                   arrBody.add(prontuario.getComplicacoesEDeformidadesPorSeguimento());
                   arrBody.add(prontuario.getConclusao());
                   arrBody.add(prontuario.getEncurtamentoPorSeguimento());
                   arrBody.add(prontuario.getForcaMuscularPorSeguimento());
                   arrBody.add(prontuario.getFrequenciaCardiaca());
                   arrBody.add(prontuario.getMetas());
                   arrBody.add(prontuario.getPressaoArterial());
                   arrBody.add(prontuario.getReflexosOsteotendineos());
                   arrBody.add(prontuario.getSensibilidadeProfunda());
                   arrBody.add(prontuario.getSensibilidadeSuperficial());
                   arrBody.add(prontuario.getSensibilidadeSuperficial());
                   arrBody.add(prontuario.getTonusMuscular());
                   arrBody.add(prontuario.getTrocasPosturais());
/*
                    Log.i("DADO", "Idade: "+prontuario.getIdade());
                   Log.i("DADO", "Nascimento: "+prontuario.getDataNascimento());
                   Log.i("DADO", "Estado Civil: "+prontuario.getEstadoCivil());
                   Log.i("DADO", "Endereco: "+prontuario.getEndereco());
                   Log.i("DADO", "Bairro: "+prontuario.getBairro());
                   Log.i("DADO", "Cidade: "+prontuario.getCidade());
                   Log.i("DADO", "CEP: "+prontuario.getCEP());
                   Log.i("DADO", "Telefone: "+prontuario.getTelefone());
                   Log.i("DADO", "Celular: "+prontuario.getCelular());
                   Log.i("DADO", "Profissao: "+prontuario.getProfissao());
                   Log.i("DADO", "Medicamentos: "+prontuario.getMedicamentos());
                   Log.i("DADO", "Orteses: "+prontuario.getOrteses());
                   Log.i("DADO", "complicacoesEDeformidadesPorSeguimento: "+prontuario.getComplicacoesEDeformidadesPorSeguimento());
                   Log.i("DADO", "encurtamentoPorSeguimento: "+prontuario.getEncurtamentoPorSeguimento());
                   Log.i("DADO", "forcaMuscularPorSeguimento: "+prontuario.getForcaMuscularPorSeguimento());
                   Log.i("DADO", "frequenciaCardiaca: "+prontuario.getFrequenciaCardiaca());
                   Log.i("DADO", "metas: "+prontuario.getMetas());
                   Log.i("DADO", "pressaoArterial: "+prontuario.getPressaoArterial());
                   Log.i("DADO", "reflexosOsteotendineos: "+prontuario.getReflexosOsteotendineos());
                   Log.i("DADO", "sensibilidadeProfunda: "+prontuario.getSensibilidadeProfunda());
                   Log.i("DADO", "sensibilidadeSuperficial: "+prontuario.getSensibilidadeSuperficial());
                   Log.i("DADO", "tonusMuscular: "+prontuario.getTonusMuscular());
                   Log.i("DADO", "trocasPosturais: "+prontuario.getTrocasPosturais());*/

                }
/*
                for(int i =0 ; i < arrBody.size(); i++){
                    Log.i("DADO", "VETOR: "+i +" "+arrBody.get(i));
                }*/
                txtBody.setText("Idade: "+arrBody.get(0).toString()+"\n"
                        +"Data de Nascimento: "+arrBody.get(26)+ "\n"
                        +"Estado Civil: "+arrBody.get(52) +"\n"
                        +"Endereço: "+arrBody.get(78)+"\n"
                        +"Bairro: "+arrBody.get(104)+"\n"
                        +"Cidade: "+arrBody.get(130)+"\n"
                        +"CEP: "+arrBody.get(156)+"\n"
                        +"Telefone: "+arrBody.get(182)+"\n"
                        +"Celular: "+arrBody.get(208)+"\n"
                        +"Profissão: "+arrBody.get(234)+"\n"
                        +"Medicamentos: "+arrBody.get(260)+"\n"
                        +"Orteses: "+arrBody.get(286)+"\n"
                        +"Complicacões e Deformidades por Seguimento: "+arrBody.get(287)+"\n"
                        +"Conclusão "+arrBody.get(288)+"\n"
                        +"Encurtamento por seguimento: "+arrBody.get(289)+"\n"
                        +"Força Muscular por seguimento: "+arrBody.get(290)+"\n"
                        +"Frequência Cardiaca: "+arrBody.get(291)+"\n"
                        +"Metas: "+arrBody.get(292)+"\n"
                        +"Pressão Arterial: "+arrBody.get(293)+"\n"
                        +"Reflexos Osteotendineos: "+arrBody.get(294)+"\n"
                        +"Sensibilidade Profunda: "+arrBody.get(295)+"\n"
                        +"Sensibilidade Superficial: "+arrBody.get(296)+"\n"
                        +"Tonus Muscular: "+arrBody.get(298)+"\n"
                        +"Trocas Posturais: "+arrBody.get(299)+"\n"


                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void recuperarCabecalho(){

        final String emailUsuario = autenticacao.getCurrentUser().getEmail();
        final String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        final DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Usuario usuario = dataSnapshot.getValue( Usuario.class );

                txtCabecalho.setText("Nome do paciente: "+usuario.getNome()
                        +"\n"+ "E-mail: "+usuario.getEmail()
                        +"\n"+ "Identificador único: "+usuario.getIdUsuario()+"\n");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public  void gerarPDF(View view){


    }

    public void enviarEmailRelatorio(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","brunomichel00@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato - RELATÓRIO ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Bom dia, segue relatório:"+"\n\n\n\n"+ txtCabecalho.getText().toString()+"\n\n"+txtBody.getText());
        startActivity(Intent.createChooser(emailIntent, "Enviar e-mail..."));
    }

    public void voltarInicio(View view){
        Intent myIntent = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivityForResult(myIntent, 0);
        finish();

    }
}
