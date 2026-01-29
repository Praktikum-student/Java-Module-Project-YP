import java.util.Scanner;

// 1. Класс Автомобиль
class Car {
    private final String name;
    private final int speed;

    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    int getDistance() {
        return speed * 24;
    }
}

// 2. Класс Гонка
class Race {
    private Car winner;

    public Car getWinner() {
        return winner;
    }

    void updateWinner(Car currentCar) {
        if (winner == null || currentCar.getDistance() > winner.getDistance()) {
            winner = currentCar;
        }
    }

    void showWinner() {
        if (winner != null) {
            System.out.println("Самая быстрая машина: " + winner.getName());
        } else {
            System.out.println("Нет данных об автомобилях");
        }
    }
}

// 3. Главный класс
public class Main {
    private static final int MIN_SPEED = 1;
    private static final int MAX_SPEED = 250;
    private static final int CAR_COUNT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Гонка автомобилей ===");
        System.out.println("Введите данные для " + CAR_COUNT + " автомобилей\n");

        Race race = new Race();

        for (int i = 0; i < CAR_COUNT; i++) {
            System.out.println("Автомобиль №" + (i + 1) + ":");

            String name = readNonEmptyString(scanner, "Введите название: ");

            int speed = readValidSpeed(scanner);

            Car car = new Car(name, speed);

            race.updateWinner(car);

            System.out.println();
        }

        race.showWinner();

        scanner.close();
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        String input;

        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Ошибка: название не может быть пустым!");
        }
    }


    private static int readValidSpeed(Scanner scanner) {
        while (true) {
            System.out.print("Введите скорость (" + MIN_SPEED + "-" + MAX_SPEED + " км/ч): ");

            if (scanner.hasNextInt()) {
                int speed = scanner.nextInt();
                scanner.nextLine();

                if (speed >= MIN_SPEED && speed <= MAX_SPEED) {
                    return speed;
                } else {
                    System.out.println("Ошибка: скорость должна быть от " + MIN_SPEED + " до " + MAX_SPEED + "!");
                }
            } else {
                System.out.println("Ошибка: введите целое число!");
                scanner.nextLine();
            }
        }
    }
}