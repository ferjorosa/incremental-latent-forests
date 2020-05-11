package experiments;

import methods.HybridMethod;

import java.util.Set;

public abstract class MixedDataExperiment {

    protected Set<HybridMethod> methods;

    public MixedDataExperiment(Set<HybridMethod> methods) {
        this.methods = methods;
    }
}
