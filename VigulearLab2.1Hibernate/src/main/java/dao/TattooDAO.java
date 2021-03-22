package dao;

import models.*;
import java.util.List;

public interface TattooDAO {
    Tattoo findByT_id(int t_id);

    void save(Tattoo tattoo);

    void update(Tattoo tattoo);

    void delete(Tattoo tattoo);

    Artist findArtistByA_id(int a_id);

    Artist findArtistByName( String a_name);

    Category findCategoryByC_id(int c_id);

    Category findCategoryByName(String c_name);

    Price findPriceByP_id(int p_id);

    Price findPriceByAmount(int p_amount);

    Size findSizeByS_id(int s_id);

    Size findSizeByName(String s_name);

    List<Tattoo> findAll();

    List<Price> findPrices();
}