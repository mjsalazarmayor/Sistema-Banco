public class CuentaAhorros extends Cuenta {
    private double tasaInteresAnual = 0.02; // 2%

    public CuentaAhorros(String numero, String titular, double saldo) {
        super(numero, titular, saldo);
    }

    @Override
    public double getLimitePermitido() {
        return 0.0;
    }

    public void aplicarInteres() {
        double interes = saldoDisponible * tasaInteresAnual;
        depositar(interes);
    }
}
