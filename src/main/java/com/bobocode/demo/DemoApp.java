package com.bobocode.demo;

import com.bobocode.bring.context.ApplicationContext;
import com.bobocode.bring.context.PackageScanningApplicationContext;
import com.bobocode.demo.model.Bicycle;
import com.bobocode.demo.model.Car;
import com.bobocode.demo.model.Track;
import com.bobocode.demo.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DemoApp {
    public static void main(String[] args) {
        ApplicationContext context = new PackageScanningApplicationContext("com.bobocode.demo");

        List<Vehicle> list = new ArrayList<>();
        list.add(context.getBean(Car.class));
        list.add((Bicycle) context.getBean("bike"));
        list.add(context.getBean("Track", Track.class));
        System.out.println("list = " + list);
        list.stream()
                .forEach(Vehicle::go);
        list.stream()
                .forEach(System.out::println);
    }
}
