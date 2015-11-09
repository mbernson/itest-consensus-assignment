package itest.consensus.impl.voter;

import itest.consensus.Voter;

public final class TrueVoter implements Voter {
    @Override
    public boolean vote() {
        return true;
    }
}
