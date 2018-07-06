package org.dqw4w9wgxcq.api.delay;

import org.rspeer.runetek.api.commons.math.Random;

import java.util.function.Supplier;

public final class Delay {
    private static Supplier<Integer> standardSupplier = () -> Random.low(1000, 2500);

    private Delay(){}

    public static int standard(){
        return standardSupplier.get();
    }

    public static void setStandardDelaySupplier(Supplier<Integer> standardSupplier) {
        Delay.standardSupplier = standardSupplier;
    }
}
