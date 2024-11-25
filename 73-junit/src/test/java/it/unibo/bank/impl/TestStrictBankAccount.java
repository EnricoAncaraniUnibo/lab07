package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        mRossi = new AccountHolder("Davide", "Rossi", 1);
        bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0, bankAccount.getBalance());
        assertEquals(mRossi, bankAccount.getAccountHolder());
        assertEquals("Davide", mRossi.getName());
        assertEquals("Rossi", mRossi.getSurname());
        assertEquals(1, mRossi.getUserID());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(1, 100);
        bankAccount.chargeManagementFees(1);
        assertEquals(100-SimpleBankAccount.MANAGEMENT_FEE-StrictBankAccount.TRANSACTION_FEE, bankAccount.getBalance());
        bankAccount = new StrictBankAccount(mRossi, 0);
        bankAccount.depositFromATM(1, 100);
        bankAccount.chargeManagementFees(1);
        assertEquals(100-SimpleBankAccount.MANAGEMENT_FEE-StrictBankAccount.ATM_TRANSACTION_FEE-StrictBankAccount.TRANSACTION_FEE, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                bankAccount.withdraw(1, -1);
            }
        });
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                bankAccount.withdraw(1, bankAccount.getBalance()+1);
            }
        });
    }
}
