public class SearchCriteria {
    private Double MinRent;
    private Double MaxRent;
    private Boolean isFurnished;
    private Boolean isAvailable;

    public SearchCriteria(Double MinRent, Double MaxRent, Boolean isFurnished, Boolean isAvailable){
        this.MinRent = MinRent;
        this.MaxRent = MaxRent;
        this.isFurnished = isFurnished;
        this.isAvailable = isAvailable;
    }

    // getters
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
