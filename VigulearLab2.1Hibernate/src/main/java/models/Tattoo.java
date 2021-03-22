package models;

import javax.persistence.*;

@Entity
@Table (name = "tattoos", uniqueConstraints = { @UniqueConstraint(columnNames = { "p_id", "c_id", "s_id", "a_id" }) })
public class Tattoo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int t_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id")
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_id")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "a_id")
    private Artist artist;

    public Tattoo() {}

    public Tattoo(Price price, Category category, Size size, Artist artist) {
        this.price = price;
        this.category = category;
        this.size = size;
        this.artist = artist;
    }

    public int getT_id() {
        return t_id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
