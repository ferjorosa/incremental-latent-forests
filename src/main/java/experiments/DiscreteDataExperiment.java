package experiments;

import methods.DiscreteMethod;

import java.util.Set;

public abstract class DiscreteDataExperiment {

    protected Set<DiscreteMethod> methods;

    public DiscreteDataExperiment(Set<DiscreteMethod> methods) {
        this.methods = methods;
    }
}
