package util;

public class SalaireHelper {

    public static double calculerSalaireNet(double salaireDeBase, double primes, double retenues) {
        return salaireDeBase + primes - retenues;
    }
}
