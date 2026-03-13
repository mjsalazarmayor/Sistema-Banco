public abstract class Cuenta {

    private String numeroCuenta;
    private String titular;
    protected double saldoDisponible;

    public Cuenta(String numeroCuenta, String titular, double saldoDisponible) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldoDisponible = saldoDisponible;
    }

    public void depositar(double monto) {
        saldoDisponible += monto;
    }

    public boolean retirar(double monto) {
        if (monto > saldoDisponible + getLimitePermitido()) {
            return false;
        }
        saldoDisponible -= monto;
        return true;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public boolean transferir(double monto, Cuenta destino) {
        if (!retirar(monto)) {
            return false;
        }
        destino.depositar(monto);
        return true;
    }

    public abstract double getLimitePermitido();

    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular()      { return titular; }
}