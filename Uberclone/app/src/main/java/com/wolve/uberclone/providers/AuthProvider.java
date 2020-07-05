package com.wolve.uberclone.providers;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {

    FirebaseAuth firebaseAuth;

    public AuthProvider(){
        firebaseAuth =FirebaseAuth.getInstance();
    }

    public Task<AuthResult>  register(String email, String password){
        return firebaseAuth.createUserWithEmailAndPassword(email,password);
    }
    public Task<AuthResult>  login(String email, String password){
        return firebaseAuth.signInWithEmailAndPassword(email,password);
    }

}
