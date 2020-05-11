package experiments;

import methods.ContinuousMethod;

import java.util.Set;

public abstract class ContinuousDataExperiment {

    protected Set<ContinuousMethod> methods;

    public ContinuousDataExperiment(Set<ContinuousMethod> methods) {
        this.methods = methods;
    }
}
