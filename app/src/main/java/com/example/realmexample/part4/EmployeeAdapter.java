package com.example.realmexample.part4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part4.realmobjects.EmployeeRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<EmployeeRO> mEmployeeResults;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part4_employee, parent, false);
        return new EmployeeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) holder;
        employeeViewHolder.textViewName.setText(mEmployeeResults.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mEmployeeResults.size();
    }

    public void setResults(RealmResults<EmployeeRO> results) {
        this.mEmployeeResults = results;
    }


    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part4_employee_name);

        }
    }

}
