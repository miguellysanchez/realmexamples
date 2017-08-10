package com.example.realmexample.part4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part4.realmobjects.EmployeeRO;
import com.example.realmexample.part4.realmobjects.ProjectRO;
import com.example.realmexample.part4.realmobjects.ProjectRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class ProjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<ProjectRO> mProjectResults;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part4_project, parent, false);
        return new ProjectViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProjectViewHolder projectViewHolder = (ProjectViewHolder) holder;
        projectViewHolder.textViewName.setText(mProjectResults.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mProjectResults.size();
    }

    public void setResults(RealmResults<ProjectRO> results) {
        this.mProjectResults = results;
    }


    public class ProjectViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part4_project_name);

        }
    }

}
