package itest.consensus.impl;

import itest.consensus.Consensus;
import itest.consensus.Voter;

import java.util.ArrayList;

public class ConsensusImpl implements Consensus {
    private ArrayList<Voter> hasVoted = new ArrayList<Voter>();

    @Override
    public boolean voting(ArrayList<Voter> voters) {
        if(!votersAreValid(voters))
            throw new RuntimeException("Cannot complete the voting because there are no voters.");

        boolean result = true;

        for(final Voter voter : voters) {
            // Cast the vote is the voter is valid and hasn't voted before.
            if(isValid(voter) && !hasVoted(voter)) {
                final boolean voteResult = voter.vote();

                hasVoted.add(voter);

                if(false == voteResult) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Check whether the voter is able to vote.
     */
    private boolean isValid(final Voter voter) {
        return null != voter;
    }

    /**
     * Check whether the voters list is usable.
     */
    private boolean votersAreValid(final ArrayList<Voter> voters) {
        return null != voters && !voters.isEmpty();
    }

    /**
     * Check whether a voter has voted before.
     */
    private boolean hasVoted(final Voter voter) {
        return hasVoted.contains(voter);
    }
}
