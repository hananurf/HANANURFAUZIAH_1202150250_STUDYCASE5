package com.example.hananurfauziah.hananurfauziah_1202150250_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Hana Nur Fauziah on 25/03/2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    private Context mcontex;
    private List<AddData> list;
    int color;

    public Adapter(Context cntx, List<AddData> list, int warna){
        this.mcontex=cntx;
        this.list=list;
        this.color=color;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) { // untuk menentukan viewholder untuk recyclerview
        View view = LayoutInflater.from(mcontex).inflate(R.layout.cardview, parent, false); // untuk membuat view baru
        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) { // untuk mensetting nilai yang didapatkan pada viewholder
        AddData data = list.get(position);
        holder.ToDo.setText(data.getTodo());
        holder.Desc.setText(data.getDesc());
        holder.Priority.setText(data.getPriority());
        holder.cardview.setCardBackgroundColor(mcontex.getResources().getColor(this.color));
    }

    @Override
    public int getItemCount() {
        return list.size();
    } // untuk mendapatkan jumlah list
    public AddData getData(int position){ // untuk mendapatkan list dari adapter
        return list.get(position);
    }
    public void deleteData(int i){ // untuk menghapus list pada TO DO LIST
        list.remove(i); // umtuk menghapus item yang terpilih
        notifyItemRemoved(i); // untuk memberi notifikasi item yang dihapus
        notifyItemRangeChanged(i, list.size());
    }

    class holder extends RecyclerView.ViewHolder{
        public TextView ToDo, Desc, Priority;
        public CardView cardview;
        public holder(View itemView){
            super(itemView);

            ToDo = itemView.findViewById(R.id.todo); // untuk mendapatkan id textview dan cardview pada layout
            Desc = itemView.findViewById(R.id.description); // untuk mendapatkan id textview dan cardview pada layout
            Priority = itemView.findViewById(R.id.number); // untuk mendapatkan id textview dan cardview pada layout
            cardview = itemView.findViewById(R.id.cardview); // untuk mendapatkan id textview dan cardview pada layout
        }
    }
}
