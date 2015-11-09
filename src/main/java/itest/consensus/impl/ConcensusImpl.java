package itest.consensus.impl;

import itest.consensus.Concensus;
import itest.consensus.Voter;

import java.util.ArrayList;

public class ConcensusImpl implements Concensus {
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
