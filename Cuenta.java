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
        if(monto <= 0){
            System.out.println("Monto inválido");
            return;
        }
        saldoDisponible += monto;
    }

    public boolean retirar(double monto) {
        if(monto <= 0){
            System.out.println("Monto inválido");
            return false;
        }
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
        if(destino == null || monto <= 0){
            return false;
        }
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