package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CityUtils {

    /**
     * Парсинг данных о городах в массив
     *
     * @return массив с данными о городах
     */
    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("/home/kirill/IdeaProjects/sbervirtual/src/main/resources/city_ru.csv"));
            while (sc.hasNextLine()) {
                // пока существует следующая строка, парсим данные в модель
                // и добавляем модель в массив
                cities.add(parse(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Парсинг полученной строки в модель City
     *
     * @param line строка с данными
     * @return {@link City}
     */
    private static City parse(String line) {
        Scanner sc = new Scanner(line);
        sc.useDelimiter(";"); // задаем разделитель в строке с данными
        sc.skip("\\d*"); // пропускаем значение номера строки (по условию)
        String name = sc.next();
        String region = sc.next();
        String district = sc.next();
        int population = sc.nextInt();
        String foundation = null;
        // так как в файле возможно отсутствие значения основания, проверяем и если оно есть, заполняем
        if (sc.hasNext()) {
            foundation = sc.next();
        }
        sc.close();

        return new City(name, region, district, population, foundation);
    }

    /**
     * Вывод в консоль списка городов
     *
     * @param cities список городов
     */
    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

}