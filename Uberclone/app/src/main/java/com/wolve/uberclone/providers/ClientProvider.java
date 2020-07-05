package com.wolve.uberclone.providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wolve.uberclone.models.Client;

public class ClientProvider {

    DatabaseReference databaseReference;

    public ClientProvider(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }

    public Task<Void> create(Client client){
        return databaseReference.child(client.getId()).setValue(client);
    }

}
