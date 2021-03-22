package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int a_id;
    @Column
    private String a_surname;
    @Column
    private String a_name;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tattoo> tattooList;

    public Artist() {}
    public Artist(String a_surname, String a_name) {
        this.a_surname = a_surname;
        this.a_name = a_name;
    }

    public void addTattoo(Tattoo tattoo) {
        tattoo.setArtist(this);
        tattooList.add(tattoo);
    }

    public void removeTattoo(Tattoo tattoo) {
        tattooList.remove(tattoo);
    }

    public int getA_id() {
        return a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_surname() {
        return a_surname;
    }

    public void setA_surname(String a_surname) {
        this.a_surname = a_surname;
    }

    public List<Tattoo> getTattooList() {
        return tattooList;
    }

    public void setTattooList(List<Tattoo> tattooList) {
        this.tattooList = tattooList;
    }

    @Override
    public String toString() {
        return a_name + " " + a_surname;
    }
}
