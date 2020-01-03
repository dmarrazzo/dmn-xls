package com.demo;

import java.util.Collections;
import java.util.List;

import org.kie.dmn.api.marshalling.DMNExtensionRegister;
import org.kie.dmn.core.compiler.DMNProfile;
import org.kie.dmn.core.compiler.DRGElementCompiler;
import org.kie.dmn.feel.runtime.FEELFunction;

public class MyDMNProfile implements DMNProfile {

    @Override
    public List<FEELFunction> getFEELFunctions() {
        return Collections.singletonList(new BKM());
    }

    @Override
    public List<DMNExtensionRegister> getExtensionRegisters() {
        return Collections.emptyList();
    }

    @Override
    public List<DRGElementCompiler> getDRGElementCompilers() {
        return Collections.emptyList();
    }

}