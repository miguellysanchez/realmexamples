package com.example.realmexample.part3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realmexample.R;
import com.example.realmexample.RealmConfigUtil;
import com.example.realmexample.part3.realmobjects.CatRO;

import io.realm.ObjectChangeSet;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by miguellysanchez on 8/9/17.
 */

public class Part3Activity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView mCatRecyclerView;
    private CatAdapter mCatAdapter;

    private TextView mTextViewFavoriteCat;
    private EditText mEditTextCatName;
    private EditText mEditTextCatColor;
    private EditText mEditTextCatNumStripes;
    private RealmResults<CatRO> mCatResults;
    private CatRO mFaveCat;


    private OrderedRealmCollectionChangeListener<RealmResults<CatRO>> mCatListListener = new OrderedRealmCollectionChangeListener<RealmResults<CatRO>>() {
        @Override
        public void onChange(RealmResults<CatRO> catROs, OrderedCollectionChangeSet changeSet) {
            if (changeSet == null) {
                mCatAdapter.notifyDataSetChanged();
                return;
            }
            OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
            for (OrderedCollectionChangeSet.Range range : deletions) {
                mCatAdapter.notifyItemRangeRemoved(range.startIndex, range.length);
            }

            OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
            for (OrderedCollectionChangeSet.Range range : insertions) {
                mCatAdapter.notifyItemRangeInserted(range.startIndex, range.length);
            }

            OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
            for (OrderedCollectionChangeSet.Range range : modifications) {
                mCatAdapter.notifyItemRangeChanged(range.startIndex, range.length);
            }

        }
    };
    private RealmObjectChangeListener<CatRO> faveCatChangeListener = new RealmObjectChangeListener<CatRO>() {
        @Override
        public void onChange(CatRO catRO, ObjectChangeSet changeSet) {
            if (changeSet.isDeleted()) {
                mFaveCat.removeAllChangeListeners();
                mFaveCat = null;
            } else {
                mTextViewFavoriteCat.setText(catRO.getName() + " | color: " + catRO.getColor() + " | # stripes: " + catRO.getNumStripes());
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3_listeners);
        Realm.setDefaultConfiguration(RealmConfigUtil.getPart3Config());
        realm = Realm.getDefaultInstance();
        initializeViews();
    }

    private void initializeViews() {
        mTextViewFavoriteCat = (TextView) findViewById(R.id.activity_part3_button_textview_fav_cat);
        mCatRecyclerView = (RecyclerView) findViewById(R.id.activity_part3_recyclerview_cats);
        mEditTextCatName = (EditText) findViewById(R.id.activity_part3_edittext_name);
        mEditTextCatColor = (EditText) findViewById(R.id.activity_part3_edittext_color);
        mEditTextCatNumStripes = (EditText) findViewById(R.id.activity_part3_edittext_num_stripes);

        mCatResults = realm.where(CatRO.class).findAllSortedAsync("dateAdded", Sort.DESCENDING);
        mCatResults.addChangeListener(mCatListListener);

        mCatAdapter = new CatAdapter(mCatResults);
        mCatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCatRecyclerView.setAdapter(mCatAdapter);
    }


    @Override
    protected void onDestroy() {
        realm.close();
        realm = null;
        super.onDestroy();
    }


    public void part3AddCat(View view) {
        final CatRO newCat = new CatRO();
        newCat.setName(mEditTextCatName.getText().toString());
        newCat.setColor(mEditTextCatColor.getText().toString());
        newCat.setNumStripes(Integer.parseInt(mEditTextCatNumStripes.getText().toString()));
        newCat.setDateAdded(System.currentTimeMillis());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(newCat);
            }
        });

    }

    public void part3RemoveLastCat(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CatRO toDeleteCat = mCatResults.get(0);
                toDeleteCat.deleteFromRealm();
            }
        });
        if(mCatResults.size() <= 0 ){
            if(mFaveCat != null) {
                mFaveCat.removeAllChangeListeners();
                mFaveCat = null;
            }
        }
    }


    public void part3SetFavoriteCat(View view) {
        if (mCatResults.size() > 0) {
            mFaveCat = mCatResults.get(0);
            mFaveCat.addChangeListener(faveCatChangeListener);
            mTextViewFavoriteCat.setText(mFaveCat.getName() + " | color: " + mFaveCat.getColor() + " | # stripes: " + mFaveCat.getNumStripes());
        } else {
            Toast.makeText(this, "Cannot fave a cat when there are no cats", Toast.LENGTH_LONG).show();
        }
    }

    public void part3ResetRealm(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }
}
