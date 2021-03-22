package VigulearJDBC;

public class TattooModel {
    private int index;
    private String artistName;
    private String artistSurname;
    private String category;
    private String sizeName;
    private String sizeNumbers;
    private int price;

    public TattooModel(){
        this("", "", "", "", "", 0);
    }
    public TattooModel(String artistName, String artistSurname, String category, String sizeName,
                       String sizeNumbers, int price) {
        this.artistName = artistName;
        this.artistSurname = artistSurname;
        this.category = category;
        this.sizeName = sizeName;
        this.sizeNumbers = sizeNumbers;
        this.price = price;
    }

    public String getArtistName(){
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistSurname() {
        return artistSurname;
    }

    public void setArtistSurname(String artistSurname){
        this.artistSurname = artistSurname;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getSizeNumbers() {
        return sizeNumbers;
    }

    public void setSizeNumbers(String sizeNumbers) {
        this.sizeNumbers = sizeNumbers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
