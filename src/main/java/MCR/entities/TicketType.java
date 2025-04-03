package MCR.entities;

public enum TicketType {
    Economy,// : 1, 1
    Business,// : 2, 5
    First;// : 5, 30

    public int moneyMultiplicator(){
        return switch (this){
            case Economy -> 1;
            case Business -> 2;
            case First -> 5;
        };
    }

    public int milesMultiplicator() {
        return switch (this) {
            case Economy -> 1;
            case Business -> 5;
            case First -> 30;
        };
    }

    public double coefficient() {
        return switch (this) {
            case Economy -> 0.1;
            case Business -> 0.5;
            case First -> 1;
        };
    }
}
