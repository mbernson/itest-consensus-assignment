package itest.consensus.impl.voter;

import itest.consensus.Voter;

import java.util.Random;

public class RandomVoter implements Voter {
    private final Random random;

    public RandomVoter() {
        this.random = new Random();
    }

    @Override
    public boolean vote() {
        return random.nextBoolean();
    }
}
