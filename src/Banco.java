import java.io.*;
import java.util.ArrayList;

public class Banco {

    private static final String ARCHIVO = "cuentas.txt";

    public static void guardarCuentas(ArrayList<Cuenta> cuentas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (Cuenta c : cuentas) {
                writer.write(c.getUsuario() + "," + c.getPin() + "," + c.getSaldo());
                writer.newLine();

                for (String mov : c.getHistorial()) {
                    writer.write("H:" + mov);
                    writer.newLine();
                }

                writer.write("END");
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error guardando cuentas");
        }
    }

    public static ArrayList<Cuenta> cargarCuentas() {
        ArrayList<Cuenta> cuentas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {

            String linea;
            Cuenta cuentaActual = null;

            while ((linea = reader.readLine()) != null) {

                if (linea.equals("END")) {
                    cuentas.add(cuentaActual);
                    continue;
                }

                if (linea.startsWith("H:")) {
                    cuentaActual.getHistorial().add(linea.substring(2));
                } else {
                    String[] datos = linea.split(",");
                    cuentaActual = new Cuenta(datos[0], datos[1], Double.parseDouble(datos[2]));
                }
            }

        } catch (IOException e) {
            return cuentas;
        }

        return cuentas;
    }

    public static Cuenta buscarUsuario(ArrayList<Cuenta> cuentas, String usuario) {
        for (Cuenta c : cuentas) {
            if (c.getUsuario().equals(usuario)) {
                return c;
            }
        }
        return null;
    }
}