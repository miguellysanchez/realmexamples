package com.example.realmexample.part3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part2.realmobjects.DogRO;
import com.example.realmexample.part3.realmobjects.CatRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private RealmResults<CatRO> catResults;

    public CatAdapter(RealmResults<CatRO> catResults) {
        this.catResults = catResults;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part3_cat, parent, false);
        CatViewHolder holder = new CatViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        CatRO cat = catResults.get(position);
        holder.textViewName.setText(cat.getName());
        holder.textViewColor.setText(cat.getColor());
        holder.textViewNumStripes.setText("" + cat.getNumStripes());
    }

    @Override
    public int getItemCount() {
        return catResults.size();
    }

    public class CatViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewColor;
        public TextView textViewNumStripes;

        public CatViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part3_cat_name);
            textViewColor = (TextView) itemView.findViewById(R.id.list_item_part3_cat_color);
            textViewNumStripes = (TextView) itemView.findViewById(R.id.list_item_part3_cat_num_stripes);
        }
    }
}
