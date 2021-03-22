package services;

import dao.TattooDAOImpl;
import models.*;
import java.util.List;

public class TattooService {
    private final TattooDAOImpl tattooDAOImpl = new TattooDAOImpl();

    public TattooService() {}

    public Tattoo findTattooById(int t_id) {
        return tattooDAOImpl.findByT_id(t_id);
    }

    public void saveTattoo(Tattoo tattoo) {
        tattooDAOImpl.save(tattoo);
    }

    public void deleteTattoo(Tattoo tattoo) {
        tattooDAOImpl.delete(tattoo);
    }

    public void updateTattoo(Tattoo tattoo) {
        tattooDAOImpl.update(tattoo);
    }

    public List<Tattoo> findAllTattoos() {
        return tattooDAOImpl.findAll();
    }

    public List<Price> findAllPrices() {
        return tattooDAOImpl.findPrices();
    }

    public Artist findArtistByA_Id(int a_id) {
        return tattooDAOImpl.findArtistByA_id(a_id);
    }

    public Artist findArtistByName(String a_name) {
        return tattooDAOImpl.findArtistByName(a_name);
    }
    public Category findCategoryByC_id(int c_id) {
        return tattooDAOImpl.findCategoryByC_id(c_id);
    }

    public Category findCategoryByName(String c_name) {
        return tattooDAOImpl.findCategoryByName(c_name);
    }

    public Price findPriceByP_id(int p_id) {
        return tattooDAOImpl.findPriceByP_id(p_id);
    }

    public Price findPriceByAmount(int p_amount) {
        return tattooDAOImpl.findPriceByAmount(p_amount);
    }

    public Size findSizeByS_id(int s_id) {
        return tattooDAOImpl.findSizeByS_id(s_id);
    }

    public Size findSizeByName(String s_name) {
        return tattooDAOImpl.findSizeByName(s_name);
    }
}
