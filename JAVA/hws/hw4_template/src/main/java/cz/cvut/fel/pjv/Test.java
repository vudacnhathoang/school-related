package cz.cvut.fel.pjv;


public class Test {

    public void start() {
        String password = "";
        BruteForceAttacker attacker = new BruteForceAttacker();
        attacker.init(new char[]{}, password);

        
        System.out.println("Trying to break password...");
        attacker.breakPassword(password.length());
        
        if (attacker.isOpened()) {
            System.out.println("[VAULT] opened, password is " + password);
        } else {
            System.out.println("[VAULT] is still closed");
        }
    }
}