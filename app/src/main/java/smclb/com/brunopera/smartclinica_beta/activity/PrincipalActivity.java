package smclb.com.brunopera.smartclinica_beta.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


import smclb.com.brunopera.smartclinica_beta.R;
import smclb.com.brunopera.smartclinica_beta.config.ConfiguracaoFirebase;
import smclb.com.brunopera.smartclinica_beta.helper.Base64Custom;
import smclb.com.brunopera.smartclinica_beta.model.Prontuario;
import smclb.com.brunopera.smartclinica_beta.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {


    private TextView txtSaudacao;
    private TextView txtEmail;
   // private TextView txtKey;

    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        recuperarDadosCadastrais();
        // FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        txtSaudacao = findViewById(R.id.txtSaudacao);
        txtEmail= findViewById(R.id.txtEmail);
        //txtKey = findViewById(R.id.txtKey);


    }

    public void desconectarUsuario(View view) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();


        autenticacao.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(PrincipalActivity.this, "Usuario desconectado!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void iniciarChat(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CHAT");
        builder.setMessage("Ainda não está disponível o chat");
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

    public void enviarEmail(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","brunomichel00@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato - ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Bom dia...");
        startActivity(Intent.createChooser(emailIntent, "Enviar e-mail..."));
    }

    public void iniciarProntuario(View view) {


        startActivity(new Intent(this, ProntuarioActivity.class));
         finish();
        //Prontuario prontuario = new Prontuario();
        //prontuario.setIdade("27");
       // prontuario.salvar();

    }



    public void verProntuario(View view) {

        startActivity(new Intent(this, RelatorioActivity.class));
        finish();

    }

    public void recuperarDadosCadastrais(){

        final String emailUsuario = autenticacao.getCurrentUser().getEmail();
        final String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        final DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Usuario usuario = dataSnapshot.getValue( Usuario.class );

                txtSaudacao.setText("Olá, "+ usuario.getNome());
                txtEmail.setText(usuario.getEmail());
               //txtKey.setText(idUsuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }




}
