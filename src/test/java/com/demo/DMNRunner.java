package com.demo;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;

public class DMNRunner {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        DMNRuntime dmnRuntime = KieRuntimeFactory.of(ks.getKieClasspathContainer().getKieBase()).get(DMNRuntime.class);
        DMNModel model = dmnRuntime.getModel("https://kiegroup.org/dmn/_8A7CB418-61A0-40D6-874E-7B17D0F910EA",
                "decision");
        DMNContext context = dmnRuntime.newContext();
        context.set("Price", 30);
        context.set("Season", "Winter");

        DMNResult evaluateAll = dmnRuntime.evaluateAll(model, context);
        System.out.println(evaluateAll);
        System.out.println(evaluateAll.getMessages());
    }
}