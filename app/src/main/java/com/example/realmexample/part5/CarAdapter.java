package com.example.realmexample.part5;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part5.realmobjects.CarRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class CarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<CarRO> mCarResults;

    public CarAdapter(RealmResults<CarRO> carResults){
        mCarResults = carResults;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part5_car, parent, false);
        return new CarViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CarViewHolder carViewHolder = (CarViewHolder) holder;
        String name = mCarResults.get(position).getName();
        carViewHolder.textViewName.setText(name);
    }

    @Override
    public int getItemCount() {
        return mCarResults.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public CarViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part5_car_name);
        }
    }

}
