package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    /**
     * 1 Сортировка и вывод в консоль списка городов.
     * Сортировка списка городов по наименованию в алфовитном порядке по убыванию без учета регистра
     *
     * @param cities список городов
     */
    public static void printFirstSort(List<City> cities) {
        // сортируем по имени без учета регистра через лямбда выражение
        cities.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        cities.forEach(System.out::println);
    }

    /**
     * 2 Cортировка и вывод в консоль списка городов.
     * Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
     * в алфавитном порядке по убыванию с учетом регистра
     *
     * @param cities список городов
     */
    public static void printSecondSort(List<City> cities) {
        // сортируем по федеральному округу и наименованию города внутри каждого федерального округа через компаратор
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        cities.forEach(System.out::println);
    }

    /**
     * Поиск города с наибольшим кол-вом жителей путем простого перебора
     *
     * @param cities массив городов
     */
    public static void searchCityMaxPopulation(List<City> cities) {
        System.out.println(cities.stream().max(Comparator.comparing(City::getPopulation)));
    }

    /**
     * Поиск количества городов в разрезе регионов через stream
     *
     * @param cities массив городов
     */
    public static void printNumberOfCitiesByRegion(List<City> cities) {
        cities.stream().
                collect(Collectors.groupingBy(City::getRegion, Collectors.counting()))
                .forEach((ct, count) -> System.out.println(ct + " - " + count));
    }

}