public abstract class Cuenta {

    private String numeroCuenta;
    private String titular;
    protected double saldoDisponible;

    public Cuenta(String numeroCuenta, String titular, double saldoDisponible) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldoDisponible = saldoDisponible;
    }

    // Incluye una validación para evitar montos negativos o cero.
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido");
            return;
        }
        saldoDisponible += monto;
    }

    // Realiza un retiro considerando el saldo actual y un límite adicional
    public boolean retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido");
            return false;
        }

        // Verifica si hay fondos suficientes sumando el saldo y el límite permitido
        if (monto > saldoDisponible + getLimitePermitido()) {
            return false; // Fondos insuficientes
        }

        saldoDisponible -= monto;
        return true;
    }

    public boolean transferir(double monto, Cuenta destino) {
        // Valida que la cuenta destino exista y el monto sea válido
        if (destino == null || monto <= 0) {
            return false;
        }

        // Intenta retirar de la cuenta origen
        if (!retirar(monto)) {
            return false; // Si falla el retiro, no se hace la transferencia
        }

        destino.depositar(monto);
        return true;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    // Método abstracto
    public abstract double getLimitePermitido();

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }
}