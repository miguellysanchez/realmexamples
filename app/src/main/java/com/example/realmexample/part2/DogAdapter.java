package com.example.realmexample.part2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part2.realmobjects.DogRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/7/17.
 */

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogViewHolder> {

    private RealmResults<DogRO> dogResults;

    public DogAdapter(RealmResults<DogRO> dogResults) {
        this.dogResults = dogResults;
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part2_dog, parent, false);
        DogViewHolder holder = new DogViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DogViewHolder holder, int position) {
        DogRO dog = dogResults.get(position);
        holder.textViewName.setText(dog.getName());
        holder.textViewBreed.setText("Breed: " + dog.getBreed());
        holder.textViewAge.setText("Age: " + dog.getAge());
    }

    @Override
    public int getItemCount() {
        return dogResults.size();
    }

    public class DogViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewBreed;
        public TextView textViewAge;

        public DogViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part2_dog_name);
            textViewBreed = (TextView) itemView.findViewById(R.id.list_item_part2_dog_breed);
            textViewAge = (TextView) itemView.findViewById(R.id.list_item_part2_dog_age);
        }
    }
}
