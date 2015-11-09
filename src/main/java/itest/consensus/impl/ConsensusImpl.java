package itest.consensus.impl;

import itest.consensus.Consensus;
import itest.consensus.Voter;

import java.util.ArrayList;

public class ConsensusImpl implements Consensus {
    @Override
    public boolean voting(ArrayList<Voter> voters) {
        if(null == voters || voters.isEmpty())
            throw new RuntimeException("Cannot complete the voting because there are no voters.");

        boolean result = true;

        for(final Voter v : voters) {
            if(null == v)
                continue;

            if(false == v.vote())
                result = false;
        }
        return result;
    }
}
