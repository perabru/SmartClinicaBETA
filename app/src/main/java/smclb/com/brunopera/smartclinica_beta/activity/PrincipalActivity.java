package smclb.com.brunopera.smartclinica_beta.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;


import java.util.ArrayList;
import java.util.List;

import smclb.com.brunopera.smartclinica_beta.R;
import smclb.com.brunopera.smartclinica_beta.adapter.AdapterMovimentacao;
import smclb.com.brunopera.smartclinica_beta.config.ConfiguracaoFirebase;
import smclb.com.brunopera.smartclinica_beta.helper.Base64Custom;
import smclb.com.brunopera.smartclinica_beta.model.Movimentacao;
import smclb.com.brunopera.smartclinica_beta.model.Prontuario;
import smclb.com.brunopera.smartclinica_beta.model.Usuario;

public class PrincipalActivity extends AppCompatActivity {


    private MaterialCalendarView calendarView;
    private TextView txtSaudacao;
    private TextView txtEmail;
   // private TextView txtKey;

    private RecyclerView recyclerView;
    private AdapterMovimentacao adapterMovimentacao;
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private  DatabaseReference movimentacaoRef;
    private ValueEventListener valueEventListenerMovimentacoes;

    private String mesAnoSelecionado;

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

        calendarView = findViewById(R.id.calendarView);

        configuraCalendarView();

        recyclerView = findViewById(R.id.recyclerMovimentos);

        //Configurar adapter

        adapterMovimentacao = new AdapterMovimentacao(movimentacoes, this);
        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);

    }

    public void recuperarHistorico(){

        final String emailUsuario = autenticacao.getCurrentUser().getEmail();
        final String idUsuario = Base64Custom.codificarBase64( emailUsuario );

        movimentacaoRef = firebaseRef.child("historico")
                                     .child(idUsuario)
                                     .child(mesAnoSelecionado);


        valueEventListenerMovimentacoes = movimentacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                movimentacoes.clear();
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Movimentacao movimentacao = dados.getValue(Movimentacao.class);
                    movimentacao.getData();
                    movimentacao.getEvolucao();
                    movimentacoes.add(movimentacao);

                }

                adapterMovimentacao.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Log.i("MES", "mes: "+mesAnoSelecionado);

    }

    public void desconectarUsuario(View view) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();


        autenticacao.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(PrincipalActivity.this, "Usuario desconectado!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void iniciarChat(View view) {


       /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        dialog.show();*/


        String url = "https://api.whatsapp.com/send?phone="+"+5512991450611";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

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

    public void configuraCalendarView(){
        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(meses);
        CalendarDay dataAtual  = calendarView.getCurrentDate();
        String mesSelecionado = String.format("%02d",(dataAtual.getMonth()+1));

        mesAnoSelecionado =  String.valueOf((mesSelecionado+""+dataAtual.getYear()));
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mesSelecionado = String.format("%02d",(date.getMonth()+1));
                mesAnoSelecionado = String.valueOf(mesSelecionado+""+date.getYear());
               // Log.i("MES", "mes: "+mesAnoSelecionado);
                movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
                recuperarHistorico();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        recuperarHistorico();
    }


}
