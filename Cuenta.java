import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Cuenta {

    private String numeroCuenta;
    private String titular;
    protected double saldoDisponible;

    private List<Transaccion> historial;

    public Cuenta(String numeroCuenta, String titular, double saldoDisponible) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldoDisponible = saldoDisponible;
        this.historial = new ArrayList<>();
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido");
            return;
        }
        saldoDisponible += monto;

        registrarTransaccion(TipoTransaccion.DEPOSITO, monto);
    }

    public boolean retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido");
            return false;
        }
        if (monto > saldoDisponible + getLimitePermitido()) {
            return false;
        }
        saldoDisponible -= monto;
        registrarTransaccion(TipoTransaccion.RETIRO, monto);

        return true;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public boolean transferir(double monto, Cuenta destino) {
        if (destino == null || monto <= 0) {
            return false;
        }
        if (!retirar(monto)) {
            return false;
        }
        destino.depositar(monto);
        registrarTransaccion(TipoTransaccion.TRANSFERENCIA, monto);
        return true;
    }

    private void registrarTransaccion(TipoTransaccion tipo, double monto){
        historial.add(new Transaccion(UUID.randomUUID().toString(), tipo, monto));
    }

    public List<Transaccion> getHistorial(){
        return historial;
    }
    
    public abstract double getLimitePermitido();

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }
}