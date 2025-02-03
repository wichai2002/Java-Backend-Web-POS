package com.backend.pos.utils;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;


public class HashUtil {
    private final BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
    private final String generalKey = "YhaVAqnBlEC64cbqBxwLX1VlViG2pZZi";
    private final String sharedKey = "SbM28ZtHq4J8PpB29oAETWmwHA8MVU6i";

    public String hasPassword(String password) {
        Hash hashedPassword = Password.hash(password)
                .addPepper(sharedKey)
                .with(this.bcrypt);
        return hashedPassword.getResult();
    }

    public String hashString(String string) {
        return bcrypt.hash(string).toString();
    }

    public Boolean passwordVerify(String password, String hashedPassword) {
        return Password.check(password, hashedPassword)
                .addPepper(this.sharedKey)
                .with(this.bcrypt);
    }
}
