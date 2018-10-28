package smclb.com.brunopera.smartclinica_beta.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

import smclb.com.brunopera.smartclinica_beta.config.ConfiguracaoFirebase;
import smclb.com.brunopera.smartclinica_beta.helper.Base64Custom;

public class Prontuario {

    private String idade, dataNascimento, idUsuario, estadoCivil, endereco, bairro,
    cidade, CEP, telefone, celular, profissao, medicamentos, orteses;


    private String pressaoArterial;
    private String frequenciaCardiaca;
    private String reflexosOsteotendineos;
    private String tonusMuscular;
    private String sensibilidadeSuperficial;
    private String sensibilidadeProfunda;
    private String trocasPosturais;
    private String forcaMuscularPorSeguimento;
    private String encurtamentoPorSeguimento;
    private String complicacoesEDeformidadesPorSeguimento;
    private String conclusao;
    private String metas;


    private static String uniquePK = ConfiguracaoFirebase.getFirebaseDatabase().push().getKey();


    public Prontuario() {
    }


    public void salvar(){


        FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        idUsuario = Base64Custom.codificarBase64( autenticacao.getCurrentUser().getEmail() );


        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        firebase.child("prontuario")
                .child(this.idUsuario )
                .child(uniquePK)
                .setValue(this);


    }


    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getOrteses() {
        return orteses;
    }

    public void setOrteses(String orteses) {
        this.orteses = orteses;
    }


    public String getPressaoArterial() {
        return pressaoArterial;
    }

    public void setPressaoArterial(String pressaoArterial) {
        this.pressaoArterial = pressaoArterial;
    }

    public String getFrequenciaCardiaca() {
        return frequenciaCardiaca;
    }

    public void setFrequenciaCardiaca(String frequenciaCardiaca) {
        this.frequenciaCardiaca = frequenciaCardiaca;
    }

    public String getReflexosOsteotendineos() {
        return reflexosOsteotendineos;
    }

    public void setReflexosOsteotendineos(String reflexosOsteotendineos) {
        this.reflexosOsteotendineos = reflexosOsteotendineos;
    }

    public String getTonusMuscular() {
        return tonusMuscular;
    }

    public void setTonusMuscular(String tonusMuscular) {
        this.tonusMuscular = tonusMuscular;
    }

    public String getSensibilidadeSuperficial() {
        return sensibilidadeSuperficial;
    }

    public void setSensibilidadeSuperficial(String sensibilidadeSuperficial) {
        this.sensibilidadeSuperficial = sensibilidadeSuperficial;
    }

    public String getSensibilidadeProfunda() {
        return sensibilidadeProfunda;
    }

    public void setSensibilidadeProfunda(String sensibilidadeProfunda) {
        this.sensibilidadeProfunda = sensibilidadeProfunda;
    }

    public String getTrocasPosturais() {
        return trocasPosturais;
    }

    public void setTrocasPosturais(String trocasPosturais) {
        this.trocasPosturais = trocasPosturais;
    }

    public String getForcaMuscularPorSeguimento() {
        return forcaMuscularPorSeguimento;
    }

    public void setForcaMuscularPorSeguimento(String forcaMuscularPorSeguimento) {
        this.forcaMuscularPorSeguimento = forcaMuscularPorSeguimento;
    }

    public String getEncurtamentoPorSeguimento() {
        return encurtamentoPorSeguimento;
    }

    public void setEncurtamentoPorSeguimento(String encurtamentoPorSeguimento) {
        this.encurtamentoPorSeguimento = encurtamentoPorSeguimento;
    }

    public String getComplicacoesEDeformidadesPorSeguimento() {
        return complicacoesEDeformidadesPorSeguimento;
    }

    public void setComplicacoesEDeformidadesPorSeguimento(String complicacoesEDeformidadesPorSeguimento) {
        this.complicacoesEDeformidadesPorSeguimento = complicacoesEDeformidadesPorSeguimento;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }


}
