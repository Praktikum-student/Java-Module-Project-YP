import java.util.Scanner ;

// 1. Класс Автомобиль
class Car {
    String name;
    int speed;

    public Car(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    int getDistance() {
        return speed * 24;
    }
}

// 2. Класс Гонка
class Race {
    Car[] cars;
    Car leader;

    public Race(Car[] cars) {
        this.cars = cars;
        findLeader();
    }

    void findLeader() {
        leader = cars[0];

        for (int i = 1; i < cars.length; i++) {
            if (cars[i].getDistance() > leader.getDistance()) {
                leader = cars[i];
            }
        }
    }

    void showWinner() {
        System.out.println("Самая быстрая машина: " + leader.name);
    }
}

// 3. Главный класс
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Гонка автомобилей ===");
        System.out.println("Введите данные для 3 автомобилей\n");

        Car[] cars = new Car[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("Автомобиль №" + (i + 1) + ":");

            System.out.print("Введите название: ");
            String name = scanner.nextLine();

            while (name.isEmpty()) {
                System.out.println("Ошибка: название не может быть пустым!");
                System.out.print("Введите название: ");
                name = scanner.nextLine();
            }

            int speed = 0;
            boolean correctSpeed = false;

            while (!correctSpeed) {
                System.out.print("Введите скорость (1-250 км/ч): ");

                if (scanner.hasNextInt()) {
                    speed = scanner.nextInt();
                    scanner.nextLine();

                    if (speed > 0 && speed <= 250) {
                        correctSpeed = true;
                    } else {
                        System.out.println("Ошибка: скорость должна быть от 1 до 250!");
                    }
                } else {
                    System.out.println("Ошибка: введите целое число!");
                    scanner.nextLine();
                }
            }

            cars[i] = new Car(name, speed);
            System.out.println();
        }

        Race race = new Race(cars);
        race.showWinner();

        scanner.close();
    }
}