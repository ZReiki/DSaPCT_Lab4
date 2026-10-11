package main.controller;

import main.dao.ManufacturerDao;
import main.dao.WatchDao;
import main.model.ManufacturerPrice;
import main.model.Watch;

import java.util.List;
import java.util.Scanner;

public class Controller {
    private final WatchDao wDao;
    private final ManufacturerDao mDao;
    private final Scanner sc;

    public Controller() {
        wDao = new WatchDao();
        mDao = new ManufacturerDao();

        sc = new Scanner(System.in);
    }

    private void printMenu() {
        System.out.println("""
                \n===== МЕНЮ МАГАЗИНУ ГОДИННИКІВ =====
                1. Вивести марки заданого типу годинників(quartz / mechanical);
                2. Вивести механічні годинники, ціна яких не перевищує задану;
                3. Вивести марки годинників, виготовлених у заданій країні;
                4. Вивести виробників, загальна сума годинників яких у магазині не перевищує задану;
                0. Вихід.
                """);
    }

    public void  start() {
        while (true) {
            printMenu();

            System.out.print("Оберіть пункт: ");
            String option = sc.nextLine().trim();

            switch (option) {
                case "1" -> handleBrandsByType();
                case "2" -> handleMechanicalWatchesByPrice();
                case "3" -> handleBrandsByCountry();
                case "4" -> handleManufacturersByTotalSum();
                case "0" -> {
                    System.out.println("Завершення роботи.");
                    return;
                }
                default -> System.out.println("Невірний пункт. Спробуйте ще раз.\n");
            }
        }
    }

    private void handleBrandsByType() {
        System.out.print("Введіть тип (quartz або mechanical): ");
        String type = sc.nextLine().trim();
        List<String> brands = wDao.getBrandsByType(type);

        System.out.println("Результат:");
        if (brands.isEmpty()) {
            System.out.println("Марки не знайдені.");
        } else {
            brands.forEach(b -> System.out.println("- " + b));
        }
    }

    private void handleMechanicalWatchesByPrice() {
        System.out.print("Введіть максимальну ціну: ");
        double price = Double.parseDouble(sc.nextLine().trim());
        List<Watch> watches = wDao.getMechanicalWatchesUnderMaxPrice(price);

        System.out.println("Результат:");
        if (watches.isEmpty()) {
            System.out.println("Годинники не знайдені.");
        } else {
            watches.forEach(w -> System.out.println("- " + w.getBrand() + ", " + w.getType() +
                    ", ціна: " + w.getPrice()));
        }
    }

    private void handleBrandsByCountry() {
        System.out.print("Введіть країну (USA, Germany, Switzerland, Japan): ");
        String country = sc.nextLine().trim();
        List<String> brands = wDao.getBrandsByCountry(country);

        System.out.println("Результат:");
        if (brands.isEmpty()) {
            System.out.println("Марки не знайдені.");
        } else {
            brands.forEach(b -> System.out.println("- " + b));
        }
    }

    private void handleManufacturersByTotalSum() {
        System.out.print("Введіть максимальну загальну суму товарів: ");
        double sum = Double.parseDouble(sc.nextLine().trim());
        List<ManufacturerPrice> list = mDao.getManufacturersByLowerTotalSum(sum);

        System.out.println("Результат:");
        if (list.isEmpty()) {
            System.out.println("Виробників не знайдено.");
        } else {
            list.forEach(m -> System.out.println("- " + m.name() + ", загальна ціна: " + m.price()));
        }
    }
}
