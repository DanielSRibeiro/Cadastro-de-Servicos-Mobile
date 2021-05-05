package com.example.techonehub.MeuAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techonehub.model.Dto.DtoServico;
import com.example.techonehub.R;

import java.util.ArrayList;

public class MeuAdapterServico extends RecyclerView.Adapter<MeuAdapterServico.MeuViewHolder> {

    ArrayList<DtoServico> arrayList;
    OnClickItemListener listener;
    public MeuAdapterServico(ArrayList<DtoServico> arrayListDtoServico, OnClickItemListener listener) {
        this.arrayList = arrayListDtoServico;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meuadapter_servico, parent, false);
        return new MeuViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.textViewEmpresa.setText(arrayList.get(position).getNm());
        holder.textViewEmail.setText(arrayList.get(position).getEmail());
        holder.textViewDt.setText("Prazo: "+arrayList.get(position).getDtPrazo());
        holder.textViewTel.setText("Tel: "+arrayList.get(position).getTel());
        holder.textViewValor.setText("R$"+arrayList.get(position).getValor());
        holder.textViewServico.setText(arrayList.get(position).getServico()+" // "+arrayList.get(position).getSistema());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{

        TextView textViewValor, textViewEmail ,textViewEmpresa, textViewTel, textViewDt, textViewServico;

        public MeuViewHolder(@NonNull View itemView, OnClickItemListener listener) {
            super(itemView);

            textViewEmpresa = itemView.findViewById(R.id.textViewEmpresa);
            textViewTel = itemView.findViewById(R.id.textViewTelServ);
            textViewEmail = itemView.findViewById(R.id.textViewEmailServ);
            textViewValor = itemView.findViewById(R.id.textViewValor);
            textViewDt = itemView.findViewById(R.id.textViewDt);
            textViewServico = itemView.findViewById(R.id.textViewServico);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongClick(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
