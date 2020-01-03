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
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.impl.DMNRuntimeImpl;
import org.kie.internal.command.CommandFactory;

/**
 * BKM
 */
public class BKM {

    public static double xlsKnowledge(DMNRuntime dmnRuntime, String season) {;
        double result = 0.9;
        StatelessKieSession kieSession = ((DMNRuntimeImpl)dmnRuntime).getInternalKnowledgeBase().newStatelessKieSession();
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