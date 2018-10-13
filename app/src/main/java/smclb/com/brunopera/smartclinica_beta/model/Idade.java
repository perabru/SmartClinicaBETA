package smclb.com.brunopera.smartclinica_beta.model;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import smclb.com.brunopera.smartclinica_beta.R;

public class Idade extends AppCompatActivity {


    private ImageView imgGravar;
    private ImageView imgCheck;
    private EditText editIdade;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    //Array que recebe primeiramente o que foi falado (nome, sobrenome e idade)
    private ArrayList<String> voiceInText;
    private String minhaIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idade);


        imgCheck = findViewById(R.id.imageView4);
        imgGravar = findViewById(R.id.imgGravar);
        editIdade = findViewById(R.id.editIdade);
        editIdade.setEnabled(false);
        imgCheck.setEnabled(false);


    }

    public  void gravarVoz(View view){
        if(editIdade.getText() != null){
            imgCheck.setEnabled(true);
        }
        openMic();
    }

    public void gravarNoBD(View view){
        //-----------colocar dentro do botão gravar
        Prontuario prontuario = new Prontuario();

        prontuario.setIdade(editIdade.getText().toString());
        prontuario.salvar();

        Toast.makeText(getApplicationContext(), "Gravado com sucesso", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(getApplicationContext(), DataNascimento.class);
        startActivityForResult(myIntent, 0);
        finish();
        //------------------------------------------------
    }

    private void openMic() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Olá, qual a sua idade?");
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
                        editIdade.setText(voiceInText.get(0).toString());
                        editIdade.setEnabled(true);

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
