package com.example.gabriel.buscando;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gabriel.buscando.Adaptador.ListaPersonaAdapter;
import com.example.gabriel.buscando.Clases.extraviado;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InicioActivity extends AppCompatActivity {

    private ListaPersonaAdapter listaPersonaAdaptador;
    private RecyclerView recyclerView;

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    DatabaseReference mensajeRef = ref.child("Extraviado");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        listaPersonaAdaptador=new ListaPersonaAdapter(this);   /////

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setAdapter(listaPersonaAdaptador);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2 );
        recyclerView.setLayoutManager(layoutManager);


        mensajeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                extraviado post=new extraviado();
                post= dataSnapshot.getValue(extraviado.class);
                post.setKey(dataSnapshot.getKey());
                dataSnapshot=dataSnapshot.child("imagenes");
                /*for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    post.set
                }*/
                listaPersonaAdaptador.adicionarExtraviado(post);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}