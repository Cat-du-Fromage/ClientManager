package MCR.entities.Account;

import MCR.entities.*;

public abstract class AccountState {
    protected StatusType status;
    protected double money;
    protected double miles;

    protected AccountState(StatusType status, double money, double miles){
        this.money = money;
        this.miles = miles;
        this.status = status;
    }

    public static AccountState createAccount(double money, double miles) {
        if (miles >= 1000 && miles < 10000) {
            return new GoldAccountState(money, miles);
        } else if (miles >= 10000) {
            return new PlatinumAccountState(money, miles);
        } else {
            return new SilverAccountState(money, miles);
        }
    }

    public StatusType getStatus() {return status;}
    public double getMoney() {return money;}
    public double getMiles() {return miles;}

    public void updateMoney(double money) {
        this.money += money;
    }

    public void updateMiles(double miles) {
        this.miles += miles;
    }

    public abstract AccountState onUpdate();

}
