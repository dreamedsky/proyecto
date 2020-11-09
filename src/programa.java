import java.util.Scanner;
public class programa {
	private static int indice=0; 
	private static String[][] alumnos=new String [100][5];
	private static int total=0;
	private static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		String user, pass, materia, grupo;

		//Parte visual del login
		System.out.println("Ingrese usuario y contraseña");
		user=sc.nextLine();
		pass=sc.nextLine();

		//Declaración del string menú
		String[] opcionesProf={"Menú: ", "1- Ingresar materia", "2- Ingresar cantidad de alumnos", "3- Ingresar alumno", "4- Mostrar promedios", "5- Mostrar informe general del grupo", "6- Salir"};

		//Declaración de variables a utilizar por el usuario
		 String apellido=""; String nombre=""; int faltas=0; int nota=0; String juicio="";


		//Estructura del programa para profesores
		while(login(user,pass)==1) {
			mostrarMenu(opcionesProf);
			int op=sc.nextInt(); 

			switch(op) {
			case 1: 
				System.out.println("¿Cuál es la materia dictada y el grupo?");
				materia=sc.nextLine();
				grupo=sc.nextLine();
			break;
			case 2:	
				System.out.println("Ingrese cantidad de alumnos");
				total=sc.nextInt();
			break;
				
			case 3: 
				for(int i=0; i<=total; i++) {
					System.out.println("Ingrese apellido: ");
					apellido=sc.next();
					System.out.println("Ingrese nombre");
					nombre=sc.next();
					System.out.println("Ingrese las inasistencias:");
					faltas=sc.nextInt();
					System.out.println("Ingrese la calificación");
					nota=sc.nextInt();
					
					ingresarAlumno(apellido, nombre, faltas, nota, juicio, indice);
				}

			break;
			
			case 4: 
					for (int i=0; i<=alumnos.length; i++) {
						for (int j=0; j<=alumnos.length; j++) {
							System.out.println(i+j);
						}
					}
				break;
			case 5:
				
			break;
			
			case 6: 
				System.out.println("Ingrese usuario y contraseña");
				user=sc.nextLine();
				pass=sc.nextLine();
			break;
	

			default: System.out.println("Por favor, ingrese una opción correcta.");

			}
			
		}


	}


	//Método login, el programa se ejecuta en función de lo que devuelva el método.
	private static int login (String user, String pass){

		String prof="profesores"; String ads="adscriptos"; String pass1="profesoresESI2020"; String pass2="adscriptosESI2020";

		if(user.equals(prof)&&pass.equals(pass1)) {
			return 1;
		}
		if(user.equals(ads)&&pass.equals(pass2)) {
			return 2;
		}
		return 3;
	}

	//Método que nos muestra las opciones del menú desde un string.
	private static void mostrarMenu(String[]opcionesProf)
	{
		for(int a=0; a<opcionesProf.length; a++) {
			System.out.println(opcionesProf[a]);
		}
	}

	public static boolean ingresarAlumno(String apellido, String nombre, int faltas, int notas, String juicio, int indice) {
		if (indice<=total) {
			alumnos[indice][0]=apellido;
			alumnos[indice][1]=nombre;
			alumnos[indice][2]=String.valueOf(faltas);
			alumnos[indice][3]=String.valueOf(notas);
			alumnos[indice][4]=juicio;
			indice++;
			return true;
		}
		return false;

	}
	
	public static int calificaciones(String[][]alumnos, int nota, int faltas) {
		for(int i=0;i<=total;i++) {
			for (int j=0;j<=total;j++) {
				if (nota==8 && faltas<15) {
					return 1;
				}
				if (nota>=4 || nota<=7 && faltas<15) {
					return 2;
				}
				if (nota==1 ||nota<=3 && faltas<33) {
					return 3;
				}
			}
		}
		return 4;
	}

}
