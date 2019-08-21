package com.ljl.enums;

/**
 * @author lvjunlong
 * @date 2019/8/21 下午12:00
 */
public enum FieldFill {

    DEFAULT(0, "默认不处理"),
    INSERT(1, "插入填充字段"),
    UPDATE(2, "更新填充字段"),
    INSERT_UPDATE(3, "插入和更新填充字段");

    private final int key;
    private final String desc;

    private FieldFill(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static FieldFill getIgnore(int key) {
        FieldFill[] fis = values();
        FieldFill[] arr$ = fis;
        int len$ = fis.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            FieldFill fi = arr$[i$];
            if (fi.getKey() == key) {
                return fi;
            }
        }

        return DEFAULT;
    }

    public int getKey() {
        return this.key;
    }

    public String getDesc() {
        return this.desc;
    }

}
