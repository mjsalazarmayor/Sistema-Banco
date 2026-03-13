public class Main {
    public static void main(String[] args) {

        CuentaAhorros ahorro = new CuentaAhorros("1234567890", "Juan Perez", 100000);
        CuentaCorriente corriente = new CuentaCorriente("0987654321", "Maria Lopez", 200000);

        ahorro.depositar(50000);
        System.out.println("Saldo ahorro: " + ahorro.getSaldoDisponible());

        boolean retiro = corriente.retirar(100000);
        System.out.println("Retiro exitoso: " + retiro);
        System.out.println("Saldo corriente: " + corriente.getSaldoDisponible());

        boolean transferencia = ahorro.transferir(30000, corriente);
        System.out.println("Transferencia exitosa: " + transferencia);
        System.out.println("Saldo ahorro tras transferencia: " + ahorro.getSaldoDisponible());
        System.out.println("Saldo corriente tras transferencia: " + corriente.getSaldoDisponible());

        ahorro.aplicarInteres();
        System.out.println("Saldo ahorro con interés: " + ahorro.getSaldoDisponible());

        System.out.println("\nHistorial de transacciones:");

        for (Transaccion t : ahorro.getHistorial()) {
            System.out.println(t);
        }
    }
}