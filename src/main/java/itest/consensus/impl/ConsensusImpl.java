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
     * The voters list is valid when:
     *
     * - It is not null
     * - It is not empty
     * - It contains at least one non-null voter
     */
    private boolean votersAreValid(final ArrayList<Voter> voters) {
        if(null == voters)
            return false;

        if(voters.isEmpty())
            return false;

        // Check if there's at least one non-null voter present
        for(final Voter v : voters) {
            if(v != null)
                return true;
        }
        // Otherwise, assume the list is invalid
        return false;
    }

    /**
     * Check whether a voter object has voted before.
     */
    private boolean hasVoted(final Voter voter) {
        return hasVoted.contains(voter);
    }
}
