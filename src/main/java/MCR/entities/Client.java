package MCR.entities;

import MCR.entities.Account.AccountState;

public class Client extends Subject implements Comparable<Client>{

    private static int id = 0;

    private String name;
    private String lastName;
    private int uniqueId;
    private AccountState accountState;
    private String lastAction = "";

    public Client(String name, String lastName, double money, double miles, StatusType status) {
        this.name = name;
        this.lastName = lastName;
        accountState = AccountState.createAccount(money, miles);
        uniqueId = id++;
    }

    public Client(String name, String lastName) {
        this(name, lastName, 0, 0, StatusType.SILVER);
    }

    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public int getUniqueId() { return uniqueId; }
    public double getMoney() { return accountState.getMoney(); }
    public double getMiles() { return accountState.getMiles(); }
    public StatusType getStatus() { return accountState.getStatus(); }
    public String getLastAction() { return lastAction; }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
        this.notifyObservers();
    }

    public void updateCredit(double amount){
        accountState.updateMoney(amount);
        accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    public void updateMiles(double miles) {
        accountState.updateMiles(miles);
        accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    public void setInfos(double money, double miles, String lastAction){
        accountState.updateMoney(money);
        accountState.updateMiles(miles);
        this.lastAction = lastAction;
        this.accountState = accountState.onUpdate();
        this.notifyObservers();
    }

    @Override
    public int compareTo(Client other) {
        int cmpNom = this.lastName.compareToIgnoreCase(other.lastName);
        return (cmpNom != 0) ? cmpNom : this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
