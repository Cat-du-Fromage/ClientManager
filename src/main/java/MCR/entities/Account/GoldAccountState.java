package MCR.entities.Account;

import MCR.entities.StatusType;

public class GoldAccountState extends AccountState{

    protected GoldAccountState(double money, double miles) {
        super(StatusType.GOLD, money, miles);
    }

    @Override
    public AccountState onUpdate() {
        if(miles < 1000)
            return new SilverAccountState(money, miles);
        else if(miles >= 10000)
            return new PlatinumAccountState(money, miles);
        else
            return this;
    }
}
