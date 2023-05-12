package granja;

import animales.Animal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Animal animal = null;
        Animal otroAnimal = null;
        int cantidadAnimales = 0;
        String codigo;
        String fecha;
        char sexo;
        double peso;
        boolean datoCorrecto = false;

        cantidadAnimales = sc.nextInt();
        sc.nextLine();

        System.out.println("Procesando animales de la granja");
        System.out.println("----------------------------------");

        for (int i = 0; i < cantidadAnimales; i++) {
            codigo = sc.nextLine();
            fecha = sc.nextLine();
            sexo = sc.next().charAt(0);
            peso = sc.nextDouble();
            sc.nextLine();

            try {
                animal = new Animal(codigo, fecha, sexo, peso);

                if (i > 0 && otroAnimal != null) {
                    if (animal.equals(otroAnimal)) {
                        System.out.println(animal.toString() + " y " + otroAnimal.toString() + " son el mismo");
                    } else {
                        System.out.println(animal.toString() + " y " + otroAnimal.toString() + " son distintos");
                    }
                }
                otroAnimal = new Animal(codigo, fecha, sexo, peso);

            } catch (IllegalArgumentException ex) {
                System.out.println("ERROR. Procesando siguiente animal");
            }
        }
    }
}
