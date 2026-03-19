import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Cuenta> cuentas = Banco.cargarCuentas();
        Cuenta cuentaActual = null;

        System.out.println("=== 🏦 BANCO MULTIUSUARIO ===");

        System.out.println("1. Crear cuenta");
        System.out.println("2. Iniciar sesión");
        int opcionInicio = scanner.nextInt();
        scanner.nextLine();

        if (opcionInicio == 1) {

            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();

            if (Banco.buscarUsuario(cuentas, usuario) != null) {
                System.out.println("❌ Usuario ya existe");
                return;
            }

            System.out.print("PIN: ");
            String pin = scanner.nextLine();

            System.out.print("Saldo inicial: ");
            double saldo = scanner.nextDouble();

            Cuenta nueva = new Cuenta(usuario, pin, saldo);
            cuentas.add(nueva);
            Banco.guardarCuentas(cuentas);

            System.out.println("✅ Cuenta creada");

        } else if (opcionInicio == 2) {

            System.out.print("Usuario: ");
            String usuario = scanner.nextLine();

            System.out.print("PIN: ");
            String pin = scanner.nextLine();

            Cuenta c = Banco.buscarUsuario(cuentas, usuario);

            if (c == null || !c.validarPin(pin)) {
                System.out.println("❌ Credenciales incorrectas");
                return;
            }

            cuentaActual = c;
        }

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Historial");
            System.out.println("5. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("💰 Saldo: " + cuentaActual.getSaldo());
                    break;

                case 2:
                    System.out.print("Monto: ");
                    double dep = scanner.nextDouble();
                    cuentaActual.depositar(dep);
                    Banco.guardarCuentas(cuentas);
                    break;

                case 3:
                    System.out.print("Monto: ");
                    double ret = scanner.nextDouble();
                    cuentaActual.retirar(ret);
                    Banco.guardarCuentas(cuentas);
                    break;

                case 4:
                    for (String mov : cuentaActual.getHistorial()) {
                        System.out.println(mov);
                    }
                    break;

                case 5:
                    System.out.println("👋 Sesión cerrada");
                    break;

                default:
                    System.out.println("❌ Opción inválida");
            }

        } while (opcion != 5);

        scanner.close();
    }
}