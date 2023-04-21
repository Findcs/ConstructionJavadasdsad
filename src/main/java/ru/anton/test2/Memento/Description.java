package ru.anton.test2.Memento;

public class Description {
    private int id;
    private int item_id;
    private String name_descr;
    private String descr_value;

    @Override
    public String toString() {
        return "Description{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", name_descr='" + name_descr + '\'' +
                ", descr_value='" + descr_value + '\'' +
                '}';
    }

    public Description(int id, int item_id, String name_descr, String descr_value) {
        this.id = id;
        this.item_id = item_id;
        this.name_descr = name_descr;
        this.descr_value = descr_value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getName_descr() {
        return name_descr;
    }

    public void setName_descr(String name_descr) {
        this.name_descr = name_descr;
    }

    public String getDescr_value() {
        return descr_value;
    }

    public void setDescr_value(String descr_value) {
        this.descr_value = descr_value;
    }
}
