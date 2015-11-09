package itest.consensus.impl;

import itest.consensus.Consensus;
import itest.consensus.Voter;

import java.util.ArrayList;

public class SimpleConsensus implements Consensus {
    @Override
    public boolean voting(ArrayList<Voter> voters) {
        for(final Voter v : voters) {
            if(!v.vote()) {
                return false;
            }
        }
        return true;
    }
}
