package MCR.entities.Account;

import MCR.entities.StatusType;

public class SilverAccountState extends AccountState{

    public SilverAccountState(double money, double miles) {
        super(StatusType.SILVER, money, miles);
    }

    @Override
    public AccountState onUpdate() {
        return miles < 1000 ? this : new GoldAccountState(miles, miles);
    }
}
