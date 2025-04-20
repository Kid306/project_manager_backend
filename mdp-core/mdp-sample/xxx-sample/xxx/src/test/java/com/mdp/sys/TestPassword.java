package com.mdp.sys;

import com.mdp.safe.client.pwd.SafePasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPassword {


    static PasswordEncoder passwordEncoder=new SafePasswordEncoder();

    public static void main(String[] args) {
        String passwordDb="$2a$10$3ns7TwEyaRMyQjgOUUF7VOfU2tB7Wx1BgR0p1vzbErlHyDDgB4QPe";
        String rawPassword="21218cca77804d2ba1922c33e0151105";

        boolean i=passwordEncoder.matches(rawPassword,passwordDb);
        System.out.println(i);
    }
}
