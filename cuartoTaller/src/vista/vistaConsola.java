package vista;

import excepciones.ExcepcionAccesoDatos;
import dominio.Libro;
import dominio.AudioLibro;
import dominio.Publicacion;
import negocio.RegistroPublicacion;

import java.util.InputMismatchException;
import java.util.Scanner;

public class vistaConsola {
    private Scanner input = new Scanner(System.in);
    private RegistroPublicacion logica;
    private int opcion;

    public vistaConsola() {
        this.logica = new RegistroPublicacion();
    }

    public void ejecutarMenu() throws ExcepcionAccesoDatos {
        agregarPrimeraPublicacion();
        mostrarMenu();
        ejecutarOpcion();
    }

    public void mostrarMenu() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("\n\tMENU DE OPCIONES");
        System.out.println("1. Registrar publicacion.");
        System.out.println("2. Ver publicaciones.");
        System.out.println("3. Buscar publicacion.");
        System.out.println("4. Eliminar publicacion.");
        System.out.println("0. Salir.");
        System.out.println("--------------------------------------------------------------------------------------------");

    }

    public int leerOpcion() {
        boolean excepcion = true;
        do {
            try {
                System.out.print("\nSeleccione una opcion: ");
                opcion = input.nextInt();
                input.nextLine();
                excepcion = false;
                return opcion;
            } catch (InputMismatchException error) {
                System.out.println("Opcion no valida, intente de nuevo");
                excepcion = true;
                input.nextLine();
            }
        } while (excepcion);
        return 0;
    }

    public void ejecutarOpcion() throws ExcepcionAccesoDatos {
        int opcion = leerOpcion();
        switch (opcion) {
            case 1:
                registrarPublicacion();
                break;
            case 2:
                mostrarPublicaciones();
                break;
            case 3:
                consultarPublicacion();
                break;
            case 4:
                eliminarPublicacion();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    public int tipoDeLibro() {
        int tipo;
        boolean excepcion = true;
        do {
            try {
                System.out.println("\nQue tipo de libro desea agregar?");
                System.out.println("1. Libro\n2. Audiolibro");
                System.out.print("Opcion: ");
                tipo = input.nextInt();
                input.nextLine();
                return tipo;
            } catch (InputMismatchException ime) {
                System.out.println("Opcion no valida, intente de nuevo");
                excepcion = true;
                input.nextLine();
            }
        } while (excepcion);
        return 0;
    }

    public void pedirDatosPublicacion(Publicacion publicacion) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.print("ingrese el ISBN: ");
        publicacion.setIsbn(input.nextLine());

        System.out.print("ingrese el titulo: ");
        publicacion.setTitulo(input.nextLine());

        System.out.print("ingrese el año de publicacion: ");
        publicacion.setAño(input.nextInt());
        input.nextLine();

        System.out.print("ingrese el autor:  ");
        publicacion.setAutor(input.nextLine());

        System.out.print("Ingrese el costo: ");
        publicacion.setCosto(input.nextDouble());
        input.nextLine();
    }

    public void pedirDatosLibro(Libro libro) throws ExcepcionAccesoDatos {
        pedirDatosPublicacion(libro);

        System.out.print("Ingrese el numero de paginas del libro: ");
        libro.setPaginas(input.nextInt());
        input.nextLine();

        System.out.print("Ingrese la edicion del libro: ");
        libro.setEdicion(input.nextInt());
        input.nextLine();
    }

    public void pedirDatosAudiolibro(AudioLibro audioLibro) throws ExcepcionAccesoDatos {
        pedirDatosPublicacion(audioLibro);

        System.out.print("Ingrese la duracion:");
        audioLibro.setDuracion(input.nextDouble());
        input.nextLine();

        System.out.print("Ingrese el formato: ");
        audioLibro.setFormato(input.nextLine());

        System.out.print("Ingrese el peso:  ");
        audioLibro.setPeso(input.nextDouble());
        input.nextLine();
    }

    public void registrarPublicacion() throws ExcepcionAccesoDatos {
        int tipo = tipoDeLibro();
        if (tipo == 1) {
            Libro libro = new Libro();
            pedirDatosLibro(libro);
            logica.agregarPublicacion(libro);
        } else if (tipo == 2) {
            AudioLibro audioLibro = new AudioLibro();
            pedirDatosAudiolibro(audioLibro);
            logica.agregarPublicacion(audioLibro);
        }
    }

    public void agregarPrimeraPublicacion() throws ExcepcionAccesoDatos {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("La lista de publicaciones se encuentra vacia," +
                " porfavor registre la primera publicacion");
        registrarPublicacion();
    }

    public void agregarMasPublicaciones() throws ExcepcionAccesoDatos {
        System.out.println("--------------------------------------------------------------------------------------------");
        registrarPublicacion();
        repetirAccion("\nDesea registrar otra publicacion?", () -> {
            try {
                agregarMasPublicaciones();
            } catch (ExcepcionAccesoDatos excepcionAccesoDatos) {
                System.out.println(excepcionAccesoDatos.getMessage());
            }
        });
    }

    public void mostrarPublicaciones() {
        System.out.println("--------------------------------------------------------------------------------------------");
        logica.getDatos().getListaPublicaciones().forEach(System.out::println);
    }


    public Publicacion buscarPublicacion() throws ExcepcionAccesoDatos {
        String isbn = input.nextLine();

        try {
            Publicacion publicacion = logica.buscarPublicacion(new Libro(isbn));
            return publicacion;
        } catch (ExcepcionAccesoDatos error) {
            System.out.println(error.getMessage());
        }
        return null;
    }

    public void consultarPublicacion() throws ExcepcionAccesoDatos {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("\nIngrese el ISBN del libro que desea consultar: ");
        Publicacion publicacion = buscarPublicacion();
        System.out.println(publicacion.toString());

        repetirAccion("Desea consultar otra publicacion?", () -> {
            try {
                consultarPublicacion();
            } catch (ExcepcionAccesoDatos excepcionAccesoDatos) {
                System.out.println(excepcionAccesoDatos.getMessage());
            }
        });
    }

    public void eliminarPublicacion() throws ExcepcionAccesoDatos {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("\nIngrese el ISBN del libro que desea eliminar: ");
        Publicacion publicacion = buscarPublicacion();

        System.out.println("\nSeguro que desea borrar esta publicacion?");
        System.out.println("1. Si "+" "+"2. No");
        int seguro = input.nextInt();

        if (seguro == 1) {
            logica.getDatos().getListaPublicaciones().remove(publicacion);
        } else if (seguro == 2) {
            System.out.println("\ncancelar ");
            ejecutarMenu();
        } else {
            throw new ExcepcionAccesoDatos("Opcion no valida");
        }

        repetirAccion("Desea borrar otra publicacion?", () -> {
            try {
                eliminarPublicacion();
            } catch (ExcepcionAccesoDatos excepcionAccesoDatos) {
                System.out.println(excepcionAccesoDatos.getMessage());
            }
        });
    }

    public void repetirAccion(String accion, Runnable funcion) throws ExcepcionAccesoDatos {
        System.out.println(accion);
        System.out.print("1. Si\n2. No\nOpcion: ");
        int seguro = input.nextInt();

        while (true) {
            if (seguro == 1) {
                funcion.run();
                break;
            } else if (seguro == 2) {
                System.out.println("\nVolviendo al menu...");
                ejecutarMenu();
                break;
            } else {
                System.out.println("Opcion no valida");
                continue;
            }
        }
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public RegistroPublicacion getLogica() {
        return logica;
    }

    public void setLogica(RegistroPublicacion logica) {
        this.logica = logica;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}
