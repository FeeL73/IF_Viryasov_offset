
package org.example;
import java.util.ArrayList;
import java.util.List;
//КОНФЛИКТт1111111
public class MainClass {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Suzuki("Grant",  180, 2008, 3, 100000, "Green", false, true, true, false));
        cars.add(new Suzuki("Vitara", 220, 2010, 5, 500000, "Yellow", true, true, true, false));
        cars.add(new Toyota("Camry", 200, 2030, 7, 10000, "Белый", true,false,false,false));
        cars.add(new Toyota("Corolla", 201, 1996, 1, 1000000, "Серый", false,false,false,false));
        cars.add(new Chery("Tiggo 4 Pro", 195, 2022, 5, 100, "Green", false, false,false,false));
        cars.add(new Chery("Tiggo 7 PtoMax", 199, 2023, 5, 50, "Black", true,false,false,false));
        cars.add(new Geely("Mongaro", 2017, 2024, 5, 0, "While", true,false,false,false));
        cars.add(new Geely("Coolray", 2008, 2023, 5, 1000, "Blue", false,false,false,false));
        cars.add(new AutoVAZ("Буханка", 999, 1950, 5, 999999, "Ржавчины", false, false, false, true));
        cars.add(new AutoVAZ("Сёмерка", 500, 1950, 5, 999999, "Ржавчины", false, false, false, true));
        cars.add(new AutoVAZ("Сёмерка", 500, 1950, 5, 999999, "Ржавчины", false, false, false, true));
        cars.add(new AutoVAZ("Сёмерка", 500, 1950, 5, 999999, "Ржавчины", false, false, false, true, 5, "ХЗ"));


        printCarsAfter2006(cars);
        changeGreenToRed(cars);
        printCarsWhenCanFly(cars);

    }

    public static void printCarsAfter2006(List<Car> cars) {
        System.out.println("\nМашина выпущена после 2006:");
        for (Car car : cars) {
            if (car.getYearCreate() > 2006) {
                System.out.println(car.getFullInfo());
            } else {
                System.out.println(car.getBrand() + " "  + " - устаревшее авто");
            }
        }
    }

    public static void changeGreenToRed(List<Car> cars) {
        System.out.println("\nПоменяли зеленый на Красный цвет:");
        for (Car car : cars) {
            if (car.getColor().equalsIgnoreCase("Green")) {
                car.setColor("Red");
                System.out.println("Поменяли цвет с Зеленого на Красный " + car.getFullInfo());
            }
        }
    }
    //МЕТОД КОТОРЫЙ ВЫДАЕТ МАШИНЫ КОТОРЫЕ УМЕЮТ ЛЕТАТЬ
    public static void printCarsWhenCanFly(List<Car> cars) {
        System.out.println("\nМАШИНА УМЕЕТ ЛЕТАТЬ:");
        for (Car car : cars) {
            if (car.canFly()) {
                System.out.println(car.getFullInfo());
            }
        }
    }

}
