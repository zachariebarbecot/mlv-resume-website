package com.rw.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Zacharie BARBECOT <z.barbecot@gmail.com>
 */
public class Hash {

    /**
     *
     * @param password the password to hash
     * @return hash string of the password
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String hashPassword(final String password)
            throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
