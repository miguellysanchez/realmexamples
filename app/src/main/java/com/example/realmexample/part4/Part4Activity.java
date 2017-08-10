package com.example.realmexample.part4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;
import com.example.realmexample.part4.realmobjects.EmployeeRO;
import com.example.realmexample.part4.realmobjects.ProjectRO;
import com.example.realmexample.part4.realmobjects.TeamRO;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Part4Activity extends AppCompatActivity {

    private Realm realm;
    private final Random random = new Random();

    private RecyclerView mRecyclerView;
    private EditText mEditTextFieldName;
    private EmployeeAdapter mEmployeeAdapter;
    private ProjectAdapter mProjectAdapter;
    private TeamAdapter mTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4_relationships);
        Realm.setDefaultConfiguration(RealmConfigUtil.getPart4Config());
        realm = Realm.getDefaultInstance();
        initializeRealmValues();
        initializeViews();
    }

    private void initializeRealmValues() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TeamRO hatch = realm.createObject(TeamRO.class, getId());
                hatch.setName("Hatch");

                TeamRO fintech = realm.createObject(TeamRO.class, getId());
                fintech.setName("Fintech");

                ProjectRO talk2 = realm.createObject(ProjectRO.class, getId());
                talk2.setName("Talk2");

                ProjectRO freenet = realm.createObject(ProjectRO.class, getId());
                freenet.setName("Freenet");

                ProjectRO paymaya = realm.createObject(ProjectRO.class, getId());
                paymaya.setName("Paymaya");

                ProjectRO lendr = realm.createObject(ProjectRO.class, getId());
                freenet.setName("Lendr");

                EmployeeRO miguel = realm.createObject(EmployeeRO.class, getId());
                EmployeeRO kim = realm.createObject(EmployeeRO.class, getId());
                EmployeeRO jade = realm.createObject(EmployeeRO.class, getId());
                EmployeeRO paolo = realm.createObject(EmployeeRO.class, getId());
                EmployeeRO harold = realm.createObject(EmployeeRO.class, getId());
                EmployeeRO sam = realm.createObject(EmployeeRO.class, getId());

                miguel.setName("Miguel");
                miguel.setTeam(hatch);
                miguel.setProjects(new RealmList<>(lendr, talk2));

                kim.setName("Kim");
                kim.setTeam(hatch);
                kim.setProjects(new RealmList<>(talk2));

                jade.setName("Jade");
                jade.setTeam(fintech);
                jade.setProjects(new RealmList<>(paymaya, lendr));

                paolo.setName("Paolo");
                paolo.setTeam(hatch);
                paolo.setProjects(new RealmList<>(paymaya, freenet, talk2));

                harold.setName("Harold");
                harold.setTeam(hatch);
                harold.setProjects(new RealmList<>(freenet));

                sam.setName("Sam");
                sam.setTeam(fintech);
                sam.setProjects(new RealmList<>(paymaya, freenet, lendr));
            }
        });
    }

    private void initializeViews() {
        mEditTextFieldName = (EditText) findViewById(R.id.activity_part4_edittext_field);
        mEmployeeAdapter = new EmployeeAdapter();
        mProjectAdapter = new ProjectAdapter();
        mTeamAdapter = new TeamAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_part4_recyclerview_company);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEmployeeAdapter.setResults(realm.where(EmployeeRO.class).findAll());
        setCurrentItemset(0);
        mRecyclerView.setAdapter(mEmployeeAdapter);
    }


    @Override
    protected void onDestroy() {
        realm.close();
        realm = null;
        super.onDestroy();
    }

    public void getAllEmployees(View view) {
        mEmployeeAdapter.setResults(realm.where(EmployeeRO.class).findAll());
        setCurrentItemset(0);
        mEmployeeAdapter.notifyDataSetChanged();
    }

    public void getAllProjects(View view) {
        mProjectAdapter.setResults(realm.where(ProjectRO.class).findAll());
        setCurrentItemset(1);
        mProjectAdapter.notifyDataSetChanged();
    }

    public void getAllTeams(View view) {
        mTeamAdapter.setResults(realm.where(TeamRO.class).findAll());
        setCurrentItemset(2);
        mTeamAdapter.notifyDataSetChanged();
    }

    public void getEmployeesWithTeam(View view) {
        RealmResults<EmployeeRO> employeesWithTeam = realm.where(EmployeeRO.class).equalTo("team.name", getFieldName()).findAll();
        mEmployeeAdapter.setResults(employeesWithTeam);
        setCurrentItemset(0);
        mEmployeeAdapter.notifyDataSetChanged();
    }

    public void getEmployeesWithProject(View view) {
        RealmResults<EmployeeRO> employeesWithProject = realm.where(EmployeeRO.class).equalTo("projects.name", getFieldName()).findAll();
        mEmployeeAdapter.setResults(employeesWithProject);
        setCurrentItemset(0);
        mEmployeeAdapter.notifyDataSetChanged();
    }

    public void getEmployeesWithProjectInverse(View view) {
        ProjectRO projectRO = realm.where(ProjectRO.class).equalTo("name", getFieldName()).findFirst();
        if(projectRO == null ){
            return;
        }
        RealmResults<EmployeeRO> employeesWithProject = projectRO.getContributors();
        mEmployeeAdapter.setResults(employeesWithProject);
        setCurrentItemset(0);
        mEmployeeAdapter.notifyDataSetChanged();
    }

    public void getProjectsWithContributor(View view) {
        RealmResults<ProjectRO> projectsWithContributor = realm.where(ProjectRO.class).equalTo("contributors.name", getFieldName()).findAll();
        mProjectAdapter.setResults(projectsWithContributor);
        setCurrentItemset(1);
        mProjectAdapter.notifyDataSetChanged();

    }


    private String getFieldName() {
        return mEditTextFieldName.getText().toString();
    }

    private long getId() {
        return random.nextLong();
    }

    public void setCurrentItemset(int currentItemset) {
        switch (currentItemset) {
            case 0:
                mRecyclerView.setAdapter(mEmployeeAdapter);
                break;
            case 1:
                mRecyclerView.setAdapter(mProjectAdapter);
                break;
            case 2:
                mRecyclerView.setAdapter(mTeamAdapter);
                break;
        }
    }
}
