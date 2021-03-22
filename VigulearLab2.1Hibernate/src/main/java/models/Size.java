package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int s_id;
    @Column
    private String s_name;
    @Column
    private String s_size;
    @Column
    private String s_descr;
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tattoo> tattooList;

    public Size() {}
    public Size(String s_name, String s_size, String s_descr) {
        this.s_name = s_name;
        this.s_size = s_size;
        this.s_descr = s_descr;
    }

    public void addTattoo(Tattoo tattoo) {
        tattoo.setSize(this);
        tattooList.add(tattoo);
    }

    public void removeTattoo(Tattoo tattoo) {
        tattooList.remove(tattoo);
    }

    public int getS_id() {
        return s_id;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_size(String s_size) {
        this.s_size = s_size;
    }

    public String getS_size() {
        return s_size;
    }

    public void setS_descr(String s_descr) {
        this.s_descr = s_descr;
    }

    public String getS_descr() {
        return s_descr;
    }

    public List<Tattoo> getTattooList() {
        return tattooList;
    }

    public void setTattooList(List<Tattoo> tattooList) {
        this.tattooList = tattooList;
    }

    @Override
    public String toString() {
        return s_name + " " + s_size + " " + s_descr;
    }

}
