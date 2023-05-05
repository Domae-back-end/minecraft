package org.plugin.model;


import org.bukkit.Material;

public class Item {
    private long item_pid;
    private String item_name;
    private String item_info;
    private String item_type;
    private String item_make;
    private String item_createday;
    private String item_fixday;


    public long getItem_pid() {
        return item_pid;
    }

    public void setItem_pid(long item_pid) {
        this.item_pid = item_pid;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_info() {
        return item_info;
    }

    public void setItem_info(String item_info) {
        this.item_info = item_info;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getItem_make() {
        return item_make;
    }

    public void setItem_make(String item_make) {
        this.item_make = item_make;
    }

    public String getItem_createday() {
        return item_createday;
    }

    public void setItem_createday(String item_createday) {
        this.item_createday = item_createday;
    }

    public String getItem_fixday() {
        return item_fixday;
    }

    public void setItem_fixday(String item_fixday) {
        this.item_fixday = item_fixday;
    }
}
