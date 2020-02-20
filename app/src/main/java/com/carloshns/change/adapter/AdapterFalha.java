package com.carloshns.change.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.carloshns.change.R;
import com.carloshns.change.entities.Quedas;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterFalha extends RecyclerView.Adapter <AdapterFalha.MyViewHolder> {

    private List<Quedas> quedas;

    public AdapterFalha(List<Quedas> quedas) {
        this.quedas = quedas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from( parent.getContext() )
                .inflate(R.layout.adapter_falha, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView tv = holder.textoData;
        ImageView img = holder.imagemDia;

        Quedas queda = quedas.get(position);

        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat fmt2 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

            Date data = fmt.parse(queda.getData());
            tv.setText(fmt2.format(data));

            Calendar cal = Calendar.getInstance();
            cal.setTime(data);
            int day = cal.get(Calendar.DAY_OF_WEEK);

            switch (day){
                case Calendar.SUNDAY:
                    holder.textoDia.setText("DO");
                    break;
                case Calendar.MONDAY:
                    holder.textoDia.setText("SE");
                    break;
                case Calendar.TUESDAY:
                    holder.textoDia.setText("TE");
                    break;
                case Calendar.WEDNESDAY:
                    holder.textoDia.setText("QA");
                    break;
                case Calendar.THURSDAY:
                    holder.textoDia.setText("QI");
                    break;
                case Calendar.FRIDAY:
                    holder.textoDia.setText("SX");
                    break;
                case Calendar.SATURDAY:
                    holder.textoDia.setText("SA");
                    break;
            }


        } catch (Exception e) {
            Log.i("INFO DB", "ERRO AO TRANSFORMAR CALENDARIO" + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return quedas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textoData, textoDia;
        ImageView imagemDia;

        public MyViewHolder (View itemView){
            super(itemView);

            textoData = itemView.findViewById(R.id.textoData);
            textoDia = itemView.findViewById(R.id.textoDia);
            imagemDia = itemView.findViewById(R.id.imagemDia);
        }
    }
}
