import java.util.ArrayList;

public class Cuenta {

    private String usuario;
    private String pin;
    private double saldo;
    private ArrayList<String> historial;

    public Cuenta(String usuario, String pin, double saldoInicial) {
        this.usuario = usuario;
        this.pin = pin;
        this.saldo = saldoInicial < 0 ? 0 : saldoInicial;
        this.historial = new ArrayList<>();
        historial.add("Cuenta creada con saldo: " + this.saldo);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public boolean validarPin(String pin) {
        return this.pin.equals(pin);
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("❌ Monto inválido");
            return;
        }
        saldo += monto;
        historial.add("Depósito: +" + monto + " | Saldo: " + saldo);
        System.out.println("✅ Depósito exitoso");
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("❌ Monto inválido");
            return;
        }

        if (saldo >= monto) {
            saldo -= monto;
            historial.add("Retiro: -" + monto + " | Saldo: " + saldo);
            System.out.println("✅ Retiro exitoso");
        } else {
            System.out.println("❌ Saldo insuficiente");
        }
    }
}