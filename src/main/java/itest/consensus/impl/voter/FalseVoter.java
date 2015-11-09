package itest.consensus.impl.voter;

import itest.consensus.Voter;

public class FalseVoter implements Voter {
    @Override
    public boolean vote() {
        return false;
    }
}
