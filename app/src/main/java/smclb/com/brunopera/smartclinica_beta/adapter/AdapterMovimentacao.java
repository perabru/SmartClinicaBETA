package smclb.com.brunopera.smartclinica_beta.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import smclb.com.brunopera.smartclinica_beta.R;
import smclb.com.brunopera.smartclinica_beta.model.Movimentacao;

/**
 * Created by Jamilton Damasceno
 */

public class AdapterMovimentacao extends RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    List<Movimentacao> movimentacoes;
    Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context) {
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);
        return new MyViewHolder(itemLista);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);

        //holder.titulo.setText(movimentacao.getDescricao());
        holder.data.setText(String.valueOf(movimentacao.getData()));
        holder.evolucao.setText(movimentacao.getEvolucao());

       // if (movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")) {
            //holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.data.setText( movimentacao.getData());
            holder.evolucao.setText(movimentacao.getEvolucao());
        //}
    }


    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo, data, evolucao;

        public MyViewHolder(View itemView) {
            super(itemView);

            //titulo = itemView.findViewById(R.id.textAdapterTitulo);
            data = itemView.findViewById(R.id.textAdapterData);
            evolucao = itemView.findViewById(R.id.textAdapterEvolucao);
        }

    }

}
