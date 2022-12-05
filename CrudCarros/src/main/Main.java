package main;

import model.Car;
import java.util.*;

public class Main {
    private static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList();
        ArrayList<String> menuOptions = new ArrayList();

        menuOptions.add("Listar");
        menuOptions.add("Criar");
        menuOptions.add("Deletar");
        menuOptions.add("Sair do programa");

        while (true) {
            showMenu(menuOptions);

            System.out.print("Digite o número da Opção: ");

            switch (getOptionInput()) {
                case 1:
                    listCars(cars);
                    break;
                case 2:
                    Car newCar = addCar(cars.size());
                    cars.add(newCar);
                    break;
                case 3:
                    int carIndex = removeCar(cars);
                    cars.remove(carIndex);
                    break;
                case 4:
                    System.exit(1);
                default:
                    break;
            }
        }
    }

    private static void showMenu(List<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("<%d> %s\n", (i + 1), menu.get(i));
        }
        System.out.println();
    }

    private static int getOptionInput() {
        String input = read.nextLine();
        int option = Integer.parseInt(input);
        System.out.println("\n");

        return option;
    }

    private static void listCars(List<Car> cars) {
        if (cars.isEmpty()) {
            return;
        }

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            System.out.printf("[%d] %s\n", i, car.getName());
        }
    }

    private static Car addCar(int lastId) {
        System.out.print("Digite o nome do seu novo carro: ");

        String carName = read.nextLine();
        Car car = new Car(lastId + 1, carName);
        
        return car;
    }

    private static int removeCar(List<Car> cars) {
        listCars(cars);
        System.out.print("Digite o número do carro que você deseja Remover: ");
        return getOptionInput();
    }

}