package com.example.techonehub.MeuAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techonehub.Dto.DtoCliente;
import com.example.techonehub.R;

import java.util.ArrayList;

public class MeuAdapterCliente extends RecyclerView.Adapter<MeuAdapterCliente.MeuViewHolder> {

    ArrayList<DtoCliente> arrayListDtocliente;
    public MeuAdapterCliente(ArrayList<DtoCliente> arrayListDtoCliente) {
        this.arrayListDtocliente = arrayListDtoCliente;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meuadapter_cliente, parent, false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.textViewNome.setText(arrayListDtocliente.get(position).getNm());
        holder.textViewEmail.setText(arrayListDtocliente.get(position).getEmail());
        holder.textViewTel.setText("Tel: "+arrayListDtocliente.get(position).getTel());
    }

    @Override
    public int getItemCount() {
        return arrayListDtocliente.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome, textViewTel, textViewEmail;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNomeC);
            textViewTel = itemView.findViewById(R.id.textViewTelC);
            textViewEmail = itemView.findViewById(R.id.textViewEmailC);
        }
    }
}


