package com.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.drools.core.ClassObjectFilter;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.command.CommandFactory;

/**
 * BKM
 */
public class BKM {
    private static KieServices ks = KieServices.Factory.get();
    private static KieContainer kieContainer = ks.getKieClasspathContainer();

    public static double xlsKnowledge(String season) {
        double result = 0.9;
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession();
        List<Command<?>> cmds = new ArrayList<>();
        cmds.add(CommandFactory.newInsert(season, "season"));
        cmds.add(CommandFactory.newFireAllRules());
        cmds.add(CommandFactory.newGetObjects(new ClassObjectFilter(Double.class), "doubles"));
        ExecutionResults results = kieSession.execute(CommandFactory.newBatchExecution(cmds));
        Collection<?> doubleCollection = (Collection<?>) results.getValue("doubles");
        for (Object object : doubleCollection) {
            result = (Double) object;
        }
        return result;
    }
}