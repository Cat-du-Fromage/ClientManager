package MCR.entities;

public enum StatusType {
    SILVER,
    GOLD,
    PLATINUM;

    public StatusType next(){
        return switch(this){
            case SILVER -> GOLD;
            case GOLD -> PLATINUM;
            case PLATINUM -> PLATINUM;
        };
    }
}
