import java.time.LocalDateTime;

public class Transaccion {

    private String id;
    private TipoTransaccion tipo;
    private double monto;
    private LocalDateTime fecha;

     public Transaccion(String id, TipoTransaccion tipo, double monto) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();

        if (!fechaValida()) {
            throw new IllegalArgumentException("Fecha de transacción inválida");
        }
    }

    public boolean fechaValida() {
        return fecha != null && !fecha.isAfter(LocalDateTime.now());
    }
    public String getId() {
        return id;
    }


    public TipoTransaccion getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        
        return fecha;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               "  Tipo: " + tipo +
               "  Monto: $" + monto +
               "  Fecha: " + fecha;
    }
}