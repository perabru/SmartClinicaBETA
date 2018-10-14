package smclb.com.brunopera.smartclinica_beta.activity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import smclb.com.brunopera.smartclinica_beta.R;
import smclb.com.brunopera.smartclinica_beta.model.Prontuario;


public class ProntuarioActivity extends AppCompatActivity {

    private final int REQ_CODE_SPEECH_OUTPUT = 1;

    //Array que recebe primeiramente o que foi falado (nome, sobrenome e idade)
    private ArrayList<String> voiceInText;


    private EditText editIdade;
    private EditText editNascimento;
    private EditText editEstadoCivil;
    private EditText editEndereco;
    private EditText editBairro;
    private EditText editCidade;
    private EditText editCEP;
    private EditText editTelefone;
    private EditText editCelular;
    private EditText editProfissao;
    private EditText editMedicamentos;
    private EditText editOrteses;



    boolean flagIdade=false;
    boolean flagNascimento=false;
    boolean flagEstadoCivil=false;
    boolean flagEndereco=false;
    boolean flagBairro =false;
    boolean flagCidade =false;
    boolean flagCEP =false;
    boolean flagTelefone =false;
    boolean flagCelular =false;
    boolean flagProfissao =false;
    boolean flagMedicamentos =false;
    boolean flagOrteses =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prontuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SMART CLÍNICA - INSTRUÇÕES");
        builder.setMessage("Antes de clicar no botão 'Gravar' tenha certeza que preencheu todos os campos."
                +"\n"+"\n"
                +"Se estiver com problemas da detecção de voz os campos são editaveis."
                +"\n"+"\n"
                +"Tenha pelo menos 10 minutos disponíveis para o cadastro."
                +"\n"+"\n"
                +"Caso haja alguma informação que não possua, basta dizer 'Não se aplica'."
                +"\n"+"\n"
                +"Faça esse processo apenas uma vez." );
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


        editIdade = findViewById(R.id.editIdade);
        editNascimento = findViewById(R.id.editNascimento);
        editEstadoCivil = findViewById(R.id.editEstadoCivil);
        editEndereco = findViewById(R.id.editEndereco);
        editBairro = findViewById(R.id.editBairro);
        editCidade = findViewById(R.id.editCidade);
        editCEP = findViewById(R.id.editCEP);
        editTelefone = findViewById(R.id.editTelefone);
        editCelular = findViewById(R.id.editCelular);
        editProfissao = findViewById(R.id.editProfissao);
        editMedicamentos = findViewById(R.id.editMedicamentos);
        editOrteses = findViewById(R.id.editOrteses);



    }

    public void gravarIdade(View view){
       flagIdade = true;
       flagNascimento = false;
        flagEstadoCivil = false;
        flagEndereco=false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;

        openMic();

    }


    public void gravarDataNasc(View view){
        flagIdade = false;
        flagNascimento = true;
        flagEstadoCivil = false;
        flagEndereco=false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();

    }


    public void gravarEstadoCivil(View view){
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = true;
        flagEndereco=false;
        flagCidade =false;
        flagBairro =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();

    }


    public void gravarEndereco(View view){
        flagEndereco=true;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

    public void gravarBairro(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =true;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

    public void gravarCidade(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =true;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

    public void gravarCEP(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = true;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

    public void gravarTelefone(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =true;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

    public void gravarCelular(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =true;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =false;
        openMic();
    }

     public void gravarProfissao(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =true;
         flagMedicamentos =false;
         flagOrteses =false;
        openMic();
    }

    public void gravarMedicamentos(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =true;
        flagOrteses =false;
        openMic();
    }

    public void gravarOrteses(View view){
        flagEndereco=false;
        flagIdade =false;
        flagNascimento = false;
        flagEstadoCivil = false;
        flagBairro =false;
        flagCidade =false;
        flagCEP = false;
        flagTelefone =false;
        flagCelular =false;
        flagProfissao =false;
        flagMedicamentos =false;
        flagOrteses =true;
        openMic();
    }


    public void GravarNoBD(View view){

        Prontuario prontuario = new Prontuario();
        prontuario.setIdade(editIdade.getText().toString());
        prontuario.setDataNascimento(editNascimento.getText().toString());
        prontuario.setEstadoCivil(editEstadoCivil.getText().toString());
        prontuario.setEndereco(editEndereco.getText().toString());
        prontuario.setBairro(editBairro.getText().toString());
        prontuario.setCidade(editCidade.getText().toString());
        prontuario.setCEP(editCEP.getText().toString());
        prontuario.setTelefone(editTelefone.getText().toString());
        prontuario.setCelular(editCelular.getText().toString());
        prontuario.setProfissao(editProfissao.getText().toString());
        prontuario.setMedicamentos(editMedicamentos.getText().toString());
        prontuario.setOrteses(editOrteses.getText().toString());
        prontuario.setComplicacoesEDeformidadesPorSeguimento("Aguardando atualização do Fisioterapeuta");
        prontuario.setConclusao("Aguardando atualização do Fisioterapeuta");
        prontuario.setEncurtamentoPorSeguimento("Aguardando atualização do Fisioterapeuta");
        prontuario.setForcaMuscularPorSeguimento("Aguardando atualização do Fisioterapeuta");
        prontuario.setFrequenciaCardiaca("Aguardando atualização do Fisioterapeuta");
        prontuario.setMetas("Aguardando atualização do Fisioterapeuta");
        prontuario.setPressaoArterial("Aguardando atualização do Fisioterapeuta");
        prontuario.setReflexosOsteotendineos("Aguardando atualização do Fisioterapeuta");
        prontuario.setSensibilidadeProfunda("Aguardando atualização do Fisioterapeuta");
        prontuario.setSensibilidadeSuperficial("Aguardando atualização do Fisioterapeuta");
        prontuario.setTonusMuscular("Aguardando atualização do Fisioterapeuta");
        prontuario.setTrocasPosturais("Aguardando atualização do Fisioterapeuta");
        prontuario.salvar();
        Toast.makeText(getApplicationContext(), "Gravado com sucesso", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }




    private void openMic() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Smart Clínica");
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000000);

        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);
        } catch (ActivityNotFoundException tim) {

            Toast.makeText(getApplicationContext(), "Opa! seu aparelho não suporta reconhecimento de voz para aplicativos", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {


                    try {
                        voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        if(flagIdade == true
                                && flagNascimento == false
                                && flagEstadoCivil==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){

                        editIdade.setText(voiceInText.get(0).toString());
                        editIdade.setEnabled(true);


                        }else if (flagIdade == false
                                && flagNascimento == true
                                && flagEstadoCivil==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){

                            editNascimento.setText(voiceInText.get(0).toString());
                            editNascimento.setEnabled(true);


                        } else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==true
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editEstadoCivil.setText(voiceInText.get(0).toString());
                            editEstadoCivil.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==true
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editEndereco.setText(voiceInText.get(0).toString());
                            editEndereco.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==true
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false) {


                            editBairro.setText(voiceInText.get(0).toString());
                            editBairro.setEnabled(true);

                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == true
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editCidade.setText(voiceInText.get(0).toString());
                            editCidade.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == true
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editCEP.setText(voiceInText.get(0).toString());
                            editCEP.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==true
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editTelefone.setText(voiceInText.get(0).toString());
                            editTelefone.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==true
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editCelular.setText(voiceInText.get(0).toString());
                            editCelular.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==true
                                && flagMedicamentos ==false
                                && flagOrteses==false){


                            editProfissao.setText(voiceInText.get(0).toString());
                            editProfissao.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==true
                                && flagOrteses==false){


                            editMedicamentos.setText(voiceInText.get(0).toString());
                            editMedicamentos.setEnabled(true);


                        }else if(flagIdade == false
                                && flagNascimento == false
                                && flagEstadoCivil ==false
                                && flagEndereco==false
                                && flagBairro ==false
                                && flagCidade == false
                                && flagCEP == false
                                && flagTelefone==false
                                && flagCelular ==false
                                && flagProfissao ==false
                                && flagMedicamentos ==false
                                && flagOrteses==true){


                            editOrteses.setText(voiceInText.get(0).toString());
                            editOrteses.setEnabled(true);
                        }
                    }catch(Exception ex){
                        Toast.makeText(getApplicationContext(), "Não foi possível gravar "+ ex.toString(), Toast.LENGTH_LONG).show();
                        //Log.i("ERRO",ex.toString());
                        openMic();
                        break;
                    }
                }


                break;

                //final do case
            }
        }
    }
}
