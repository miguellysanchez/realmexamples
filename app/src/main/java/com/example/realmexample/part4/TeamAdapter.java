package com.example.realmexample.part4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realmexample.R;
import com.example.realmexample.part4.realmobjects.EmployeeRO;
import com.example.realmexample.part4.realmobjects.ProjectRO;
import com.example.realmexample.part4.realmobjects.TeamRO;

import io.realm.RealmResults;

/**
 * Created by miguellysanchez on 8/10/17.
 */

public class TeamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RealmResults<TeamRO> mTeamResults;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_part4_team, parent, false);
        return new TeamViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TeamViewHolder teamViewHolder = (TeamViewHolder) holder;
        teamViewHolder.textViewName.setText(mTeamResults.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTeamResults.size();
    }

    public void setResults(RealmResults<TeamRO> results) {
        this.mTeamResults = results;
    }


    public class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;

        public TeamViewHolder(View itemView) {
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.list_item_part4_team_name);

        }
    }

}
