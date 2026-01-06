public class SearchCriteria {
    private String location;
    private Double MinRent;
    private Double MaxRent;
    private Boolean isFurnished;
    private Boolean isAvailable;

    public SearchCriteria(String location, Double MinRent, Double MaxRent, Boolean isFurnished, Boolean isAvailable){
        this.location = location;
        this.MinRent = MinRent;
        this.MaxRent = MaxRent;
        this.isFurnished = isFurnished;
        this.isAvailable = isAvailable;
    }

    // getters
    public String GetLocation(){
        return location;
    }

    public Double GetMinRent(){
        return MinRent;
    }

    public Double GetMaxRent(){
        return MaxRent;
    }

    public Boolean GetisFurnished(){
        return isFurnished;
    }

    public Boolean GetisAvailable(){
        return isAvailable;
    }
}
