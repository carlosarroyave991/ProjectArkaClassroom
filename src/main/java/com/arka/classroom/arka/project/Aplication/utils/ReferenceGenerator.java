package com.arka.classroom.arka.project.Aplication.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ReferenceGenerator {
    //Hashset se usa para almacenar las referencias ya usadas
    private static final Set<Long> usedReferences = new HashSet<>();
    //Random para generar numeros aleatorios
    private static final Random random = new Random();

    //Se usa para generar referencias únicas.
    public static synchronized Long generateReference() {
        Long reference;
        do {
            reference = random.nextLong(1000000, 9999999); // Generar un número aleatorio de 7 dígitos
        } while (usedReferences.contains(reference));
        usedReferences.add(reference);
        return reference;
    }
}
