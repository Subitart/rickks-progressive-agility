package org.dqw4w9wgxcq.api.identifiable;

import org.rspeer.runetek.api.commons.Identifiable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public final class Preds {
    private Preds(){}

    public static Predicate<Identifiable> name(String... names){
        final Set<String> nameSet = new HashSet<>(Arrays.asList(names));
        return identifiable -> nameSet.contains(identifiable.getName());
    }

    public static Predicate<Identifiable> id(Integer... ids){
        final Set<Integer> idSet = new HashSet<>(Arrays.asList(ids));
        return identifiable -> idSet.contains(identifiable.getId());
    }

    public static Predicate<Identifiable> prefix(String... prefixes){
        return identifiable -> {
            String name = identifiable.getName();
            return Arrays.stream(prefixes).anyMatch(name::startsWith);
        };
    }
}
