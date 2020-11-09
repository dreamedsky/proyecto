import java.sql.DriverManager;
import java.time.Clock;
import java.util.Scanner;

public class programa {
    private static int indice = 0;
    private static String[][] alumnos = new String[100][5];
    private static int total = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String user, pass, materia, grupo;

        //Parte visual del login
        System.out.println("Ingrese usuario y contraseña");
        user = sc.nextLine();
        pass = sc.nextLine();

        //Declaración del string menú
        String[] opcionesProf = {"Menú: ", "1- Ingresar materia", "2- Ingresar cantidad de alumnos", "3- Ingresar alumno", "4- Mostrar promedios", "5- Mostrar informe general del grupo", "6- Salir"};

        //Estructura del programa para profesores
        while (login(user, pass) == 1) {
            mostrarMenu(opcionesProf);
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println("¿Cuál es la materia dictada y el grupo?");
                    materia = sc.nextLine();
                    grupo = sc.nextLine();
                    break;
                case 2:
                    System.out.println("Ingrese cantidad de alumnos");
                    total = sc.nextInt();
                    break;

                case 3:
                    for (int i = 0; i <= total; i++) {
                        System.out.println("Ingrese ci: ");
                        String ci = sc.next();
                        System.out.println("Ingrese apellido: ");
                        String apellido = sc.next();
                        System.out.println("Ingrese nombre");
                        String nombre = sc.next();
                        System.out.println("Ingrese las inasistencias:");
                        String faltas = sc.next();
                        System.out.println("Ingrese la calificación");
                        String nota = sc.next();

                        ingresarAlumno(ci, apellido, nombre, faltas, nota, indice);
                    }

                    break;

                case 4:
                    for (int i = 0; i <= alumnos.length; i++) {
                        System.out.println("-------------------------------------------------------------------");
                        System.out.println("Mostrando datos del alumno cuya ci es: "+ alumnos[i][tablaAlumnos.ci.valor]);
                        System.out.println("Nombre: "+ alumnos[i][tablaAlumnos.nombre.valor]);
                        System.out.println("Apellido: "+ alumnos[i][tablaAlumnos.apellido.valor]);
                        System.out.println("Cant. faltas: "+ alumnos[i][tablaAlumnos.faltas.valor]);
                        System.out.println("Promedio: "+ alumnos[i][tablaAlumnos.notas.valor]);
                        System.out.println("Juicio: "+ alumnos[i][tablaAlumnos.juicio.valor]);
                        System.out.println("-------------------------------------------------------------------");
                    }
                    break;
                case 5:

                    break;

                case 6:
                    System.out.println("Ingrese usuario y contraseña");
                    user = sc.nextLine();
                    pass = sc.nextLine();
                    break;


                default:
                    System.out.println("Por favor, ingrese una opción correcta.");
            }
        }
    }

    /**
     * Método login, el programa se ejecuta en función de lo que devuelva el método.
     * @param user
     * @param pass
     * @return
     */
    private static int login(String user, String pass) {

        String prof = "profesores";
        String ads = "adscriptos";
        String pass1 = "profesoresESI2020";
        String pass2 = "adscriptosESI2020";

        if (user.equals(prof) && pass.equals(pass1)) {
            return 1;
        }
        if (user.equals(ads) && pass.equals(pass2)) {
            return 2;
        }
        return 3;
    }

    /**
     * Método que nos muestra las opciones del menú desde un string.
     * @param opcionesProf Array de opciones a mostrar.
     */
    private static void mostrarMenu(String[] opcionesProf) {
        for (int a = 0; a < opcionesProf.length; a++) {
            System.out.println(opcionesProf[a]);
        }
    }

    public static boolean ingresarAlumno(String ci, String apellido, String nombre, String faltas, String notas, int indice) {
        if (indice <= total) {
            alumnos[indice][tablaAlumnos.ci.valor] = ci;
            alumnos[indice][tablaAlumnos.nombre.valor] = nombre;
            alumnos[indice][tablaAlumnos.apellido.valor] = apellido;
            alumnos[indice][tablaAlumnos.faltas.valor] = faltas;
            alumnos[indice][tablaAlumnos.notas.valor] = notas;
            indice++;
            return true;
        }
        return false;
    }

    public static int calificaciones(String ci) {
        try {
            for (int i = 0; i <= alumnos.length; i++) {
                if (alumnos[i][0].equals(ci)) {
                    if (Integer.parseInt(alumnos[i][3]) == 8 && Integer.parseInt(alumnos[i][2]) < 15) {
                        return Juicio.Promovido.valor();
                    }
                    if (Integer.parseInt(alumnos[i][3]) >= 4 || Integer.parseInt(alumnos[i][3]) <= 7 && Integer.parseInt(alumnos[i][2]) < 15) {
                        return Juicio.Diciembre.valor();
                    }
                    if (Integer.parseInt(alumnos[i][2]) == 1 || Integer.parseInt(alumnos[i][2]) <= 3 && Integer.parseInt(alumnos[i][2]) < 33) {
                        return Juicio.Febrero.valor();
                    }
                }
            }
            return -1;
        } catch (NumberFormatException e) {
            System.out.println("Error al parsear");
            return -1;
        }
    }

    /**
     * Enum para facilitar los return de juicio.
     */
    private enum Juicio {
        Promovido(1),
		Diciembre(2),
		Febrero(3),
		Libre(4);

        private int valor;

        Juicio(int valor) {
            this.valor = valor;
        }

        public int valor() {
            return valor;
        }
    }

    /**
     * Enum para facilitar la tabla de alumnos.
     */
	private enum tablaAlumnos {
		ci(0),
		nombre(1),
		apellido(2),
		faltas(3),
		notas(4),
		juicio(5);

		private int valor;

		tablaAlumnos(int valor) {
			this.valor = valor;
		}

		public int valor() {
			return valor;
		}
	}
}
