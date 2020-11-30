package com.example.techonehub.MeuAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.techonehub.Dto.DtoSocio;
import com.example.techonehub.R;

import java.util.ArrayList;

public class MeuAdapterSocio extends RecyclerView.Adapter<MeuAdapterSocio.MeuViewHolder> {

    ArrayList<DtoSocio> arrayListDtosocio;
    public MeuAdapterSocio(ArrayList<DtoSocio> arrayListSocio) {
    this.arrayListDtosocio = arrayListSocio;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.meuadapter_socio, parent, false);
        return new MeuViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.textViewNome.setText(arrayListDtosocio.get(position).getNm());
        holder.textViewCPF.setText("CPF: "+arrayListDtosocio.get(position).getCpf());
        holder.textViewEspecialidade.setText(arrayListDtosocio.get(position).getEspec());
    }

    @Override
    public int getItemCount() {
        return arrayListDtosocio.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNome, textViewCPF, textViewEspecialidade;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNome = itemView.findViewById(R.id.textViewNomeS);
            textViewCPF = itemView.findViewById(R.id.textViewCPFS);
            textViewEspecialidade = itemView.findViewById(R.id.textViewEspecialidadeS);
        }
    }
}
