package com.example.demo.utils;

import com.example.demo.annotation.DBTable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

public class TableCreator {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("arguments: annotated classess");
            System.exit(0);
        }

        for(String className : args){
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println(
                        "NO DBTable annotations in class " + className
                );
                continue;
            }
            // 获取表名
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = tableName.toUpperCase(Locale.ROOT);

                ArrayList<String> columnDefs = new ArrayList<>();
                for (Field field : cl.getDeclaredFields()) {
                    String columName = null;
                }

            }

        }
    }
}
