package MCR.entities.Account;

import MCR.entities.StatusType;

public class PlatinumAccountState extends AccountState {

    private boolean locked;

    protected PlatinumAccountState(double money, double miles) {
        super(StatusType.PLATINUM, money, miles);
    }

    private boolean checkLocked() {
        if(!locked && getMoney() >= 100000){
            locked = true;
        }
        return locked;
    }

    @Override
    public AccountState onUpdate() {
        if(checkLocked()) return this;
        return miles >= 10000 ? this : new GoldAccountState(miles, miles);
    }
}
