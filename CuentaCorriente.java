public class CuentaCorriente extends Cuenta{
    private double limiteDeSobregiro = 500000;

    public CuentaCorriente(String numero, String titular, double saldo){
        super(numero, titular, saldo);
    }

    @Override
    public double getLimitePermitido(){
        return limiteDeSobregiro;
    }
}