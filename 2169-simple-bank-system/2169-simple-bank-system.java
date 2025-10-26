class Bank {

    private long[] balance;

    public Bank(long[] balance) {
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        // Check valid accounts
        if (!isValid(account1) || !isValid(account2)) return false;
        // Check sufficient balance
        if (balance[account1 - 1] < money) return false;

        balance[account1 - 1] -= money;
        balance[account2 - 1] += money;
        return true;
    }
    
    public boolean deposit(int account, long money) {
        if (!isValid(account)) return false;

        balance[account - 1] += money;
        return true;
    }
    
    public boolean withdraw(int account, long money) {
        if (!isValid(account)) return false;
        if (balance[account - 1] < money) return false;

        balance[account - 1] -= money;
        return true;
    }

    // Helper function to check valid account index
    private boolean isValid(int account) {
        return account >= 1 && account <= balance.length;
    }
}

/**
 * Example usage:
 * 
 * Bank bank = new Bank(new long[]{10, 100, 20, 50, 30});
 * System.out.println(bank.withdraw(3, 10));  // true
 * System.out.println(bank.transfer(5, 1, 20)); // true
 * System.out.println(bank.deposit(5, 20)); // true
 * System.out.println(bank.transfer(3, 4, 15)); // false
 * System.out.println(bank.withdraw(10, 50)); // false
 */
