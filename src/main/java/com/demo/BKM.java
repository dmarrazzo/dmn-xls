package com.demo;

import java.util.Collection;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * BKM
 */
public class BKM {
    private static KieServices ks = KieServices.Factory.get();
    private static KieContainer kieContainer = ks.getKieClasspathContainer();

    public static double xlsKnowledge(String season) {
        double result = 0.9;
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(season);

        int fireAllRules = kieSession.fireAllRules();
        System.out.println("fireAllRules: "+fireAllRules);

        Collection<? extends Object> objects = kieSession.getObjects();

        for (Object object : objects) {
            if (object instanceof Double)
                result = (Double) object;
        }
        kieSession.dispose();

        return result;
    }
}