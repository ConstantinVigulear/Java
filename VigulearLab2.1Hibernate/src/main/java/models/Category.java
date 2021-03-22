package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
    @Column
    private String c_name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tattoo> tattooList;

    public Category(){}
    public Category(String c_name) {
        this.c_name = c_name;
    }

    public void addTattoo(Tattoo tattoo) {
        tattoo.setCategory(this);
        tattooList.add(tattoo);
    }

    public void removeTattoo(Tattoo tattoo) {
        tattooList.remove(tattoo);
    }

    public int getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public List<Tattoo> getTattooList() {
        return tattooList;
    }

    public void setTattooList(List<Tattoo> tattooList) {
        this.tattooList = tattooList;
    }

    @Override
    public String toString() {
        return c_name;
    }
}
