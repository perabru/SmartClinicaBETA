package smclb.com.brunopera.smartclinica_beta.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.text.DateFormat;
import java.util.Calendar;

import smclb.com.brunopera.smartclinica_beta.config.ConfiguracaoFirebase;

public class Usuario {

    private String idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String dataAtual;




    public Usuario() {

    }
    public void salvar(){
        DatabaseReference fireabase = ConfiguracaoFirebase.getFirebaseDatabase();
        fireabase.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    public String getNome() {
        return nome;
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


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataAtual() {
        return dataAtual;
    }

    public void setDataAtual(String dataAtual) {
        this.dataAtual = dataAtual;
    }
}
