package com.mdp.dm.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DmValid {
    public static void main(String[] args) {
        String sql = " INSERT INTO users VALUES (1, 'Alice');\n"
                + "xxxxUPDATE users SET age = 20 WHERE name = 'Bob';\n"
                + "DELETE FROM users WHERE id = 3; \n"
                + "SELECT * FROM users;alert dropxxxx";


    }

    public static List<String> getSqlOper(String sql){
        String patternString = "\\b(INSERT|UPDATE|DELETE|SELECT|DROP|ALERT)\\b";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);
        List<String> list=new ArrayList<>();
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            list.add(matcher.group(1));
        }
        return list;
    }
}
