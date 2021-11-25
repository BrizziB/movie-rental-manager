package it.unifi.ing.stlab.movierentalmanager.model.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHash {

    public String createPasswordKey( String password ) {
        if ( password == null )
            throw new IllegalArgumentException( "Password cannot be null" );

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update( password.getBytes() );
            return new String( Base64.getEncoder().encode(md.digest()) );
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException( "Algorithm not found" );
        }
    }

}
