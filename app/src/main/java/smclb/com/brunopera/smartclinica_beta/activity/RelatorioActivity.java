package smclb.com.brunopera.smartclinica_beta.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import smclb.com.brunopera.smartclinica_beta.model.Usuario;

public class RelatorioActivity extends AppCompatActivity {

    private TextView txtCabecalho;
    private TextView txtBody;
    private TextView textView;

    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txtCabecalho = findViewById(R.id.txtCabecalho);
        txtBody = findViewById(R.id.txtBody);
        textView = findViewById(R.id.textView);
        recuperarCabecalho();


            recuperarBody();


    }

    public void recuperarBody() {

        final String emailUsuario = autenticacao.getCurrentUser().getEmail();
        final String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        final DatabaseReference usuarioRef = firebaseRef.child("prontuario").child(idUsuario);

        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    ArrayList<String> arrBody = new ArrayList<String>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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

                    }
                /*for(int i =0 ; i < arrBody.size(); i++){
                    Log.i("DADO", "VETOR: "+i +" "+arrBody.get(i));
                }*/

                    txtBody.setText("Idade: " + arrBody.get(0).toString() + "\n"
                            + "Data de Nascimento: " + arrBody.get(1) + "\n"
                            + "Estado Civil: " + arrBody.get(2) + "\n"
                            + "Endereço: " + arrBody.get(3) + "\n"
                            + "Bairro: " + arrBody.get(4) + "\n"
                            + "Cidade: " + arrBody.get(5) + "\n"
                            + "CEP: " + arrBody.get(6) + "\n"
                            + "Telefone: " + arrBody.get(7) + "\n"
                            + "Celular: " + arrBody.get(8) + "\n"
                            + "Profissão: " + arrBody.get(9) + "\n"
                            + "Medicamentos: " + arrBody.get(10) + "\n"
                            + "Orteses: " + arrBody.get(11) + "\n"
                            + "Complicacões e Deformidades por Seguimento: " + arrBody.get(12) + "\n"
                            + "Conclusão " + arrBody.get(13) + "\n"
                            + "Encurtamento por seguimento: " + arrBody.get(14) + "\n"
                            + "Força Muscular por seguimento: " + arrBody.get(15) + "\n"
                            + "Frequência Cardiaca: " + arrBody.get(16) + "\n"
                            + "Metas: " + arrBody.get(17) + "\n"
                            + "Pressão Arterial: " + arrBody.get(18) + "\n"
                            + "Reflexos Osteotendineos: " + arrBody.get(19) + "\n"
                            + "Sensibilidade Profunda: " + arrBody.get(20) + "\n"
                            + "Sensibilidade Superficial: " + arrBody.get(21) + "\n"
                            + "Tonus Muscular: " + arrBody.get(23) + "\n"
                            + "Trocas Posturais: " + arrBody.get(24) + "\n"


                    );
                }catch (Exception ex){
                    Toast.makeText(RelatorioActivity.this,"Prontuario incompleto, finalize-o antes!", Toast.LENGTH_SHORT).show();
                    txtBody.setText("INCOMPLETO");
                }
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

/*
    public  void gerarNotificacao(View view){

        addNotification();
    }




    // Creates and displays a notification
    private void addNotification() {



        // Builds your notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Smart Clinica")
                .setContentText("Seu fisioterapeuta fez uma atualização!");

        // Creates the intent needed to show the notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

*/


public void compartilhar(View view){
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString()+"\n"
            + txtCabecalho.getText().toString() +"\n"
            +txtBody.getText().toString());
    sendIntent.setType("text/plain");
    startActivity(sendIntent);
}


    public void atualizar(View view){
        startActivity(new Intent(this, RelatorioActivity.class));
        finish();



    }

    public void gerarPDF(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SMART CLÍNICA");
        builder.setMessage("Não disponível ainda");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();



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
