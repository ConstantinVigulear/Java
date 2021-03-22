package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int p_id;
    @Column
    private int p_amount;
    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tattoo> tattooList;

    public Price(){}
    public Price(int p_amount){
        this.p_amount = p_amount;
    }

    public void addTattoo(Tattoo tattoo) {
        tattoo.setPrice(this);
        tattooList.add(tattoo);
    }

    public void removeTattoo(Tattoo tattoo) {
        tattooList.remove(tattoo);
    }

    public int getP_id() {
        return p_id;
    }

    public int getP_amount() {
        return p_amount;
    }
    public void setP_amount(int p_amount) {
        this.p_amount = p_amount;
    }

    public List<Tattoo> getTattooList() {
        return tattooList;
    }

    private void setTattooList( List<Tattoo> tattooList) {
        this.tattooList = tattooList;
    }


    @Override
    public String toString() {
        return Integer.toString(p_amount);
    }
}
