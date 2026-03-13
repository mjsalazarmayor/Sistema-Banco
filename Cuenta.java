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

    // Incluye una validación para evitar montos negativos o cero.
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido");
            return;
        }
        saldoDisponible += monto;

        registrarTransaccion(TipoTransaccion.DEPOSITO, monto);
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
        registrarTransaccion(TipoTransaccion.RETIRO, monto);
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
        saldoDisponible -= monto;
        registrarTransaccion(TipoTransaccion.TRANSFERENCIA, monto);
        return true;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    private void registrarTransaccion(TipoTransaccion tipo, double monto){
        historial.add(new Transaccion(UUID.randomUUID().toString(), tipo, monto));
    }

    public List<Transaccion> getHistorial(){
        return historial;
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