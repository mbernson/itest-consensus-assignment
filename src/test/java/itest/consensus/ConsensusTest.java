package itest.consensus;

import itest.consensus.impl.ConsensusImpl;
import itest.consensus.impl.SimpleConsensus;
import itest.consensus.impl.voter.FalseVoter;
import itest.consensus.impl.voter.RandomVoter;
import itest.consensus.impl.voter.TrueVoter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class ConsensusTest {
    @Parameter
    public Consensus consensus;

    private ArrayList<Voter> voters;

    @Parameters
    public static Collection<Consensus> consensusCollection() {
        return Arrays.asList(
                new ConsensusImpl()
//                new SimpleConsensus()
        );
    }

    @Before
    public void setUp() throws Exception {
        voters = new ArrayList<Voter>();
    }

    /**
     * Specificatie 1: De returnvalue van voting is alleen false als minimaal één stemmer als stem false geeft.
     */
    @Test
    public void testVotingReturnsFalseWhenOneVoterVotesFalse() {
        voters.add(new FalseVoter());
        voters.add(new TrueVoter());
        voters.add(new FalseVoter());
        voters.add(new RandomVoter());

        assertFalse(consensus.voting(voters));
    }

    /**
     * Specificatie 2: Als alle stemgerechtigden true stemmen dan moet de returnvalue van `voting()` true zijn.
     */
    @Test
    public void testVotingReturnsTrueWhenAllVotersVoteTrue() {
        voters.add(new TrueVoter());
        voters.add(new TrueVoter());
        voters.add(new TrueVoter());

        assertTrue(consensus.voting(voters));
    }

    @Test
    public void testVotingReturnsTrueWhenAllVotersVoteTrueAndListContainsNull() {
        voters.add(new TrueVoter());
        voters.add(null);
        voters.add(new TrueVoter());
        voters.add(null);

        assertTrue(consensus.voting(voters));
    }

    /**
     * Specificatie 3: Als specificatie 1 en 2 zich niet voordoen moet er een exception worden opgeworpen.
     */
    @Test(expected = Exception.class)
    public void testExceptionIsThrownWhenNobodyVoted() {
        consensus.voting(voters);
    }

    @Test(expected = Exception.class)
    public void testExceptionIsThrownWhenAllVotersAreNull() {
        voters.add(null);
        voters.add(null);
        voters.add(null);

        consensus.voting(voters);
    }

    /**
     * Specificatie 4: Iedere valide stemmer (dat wil zeggen, iedere niet null stemmer) moet stemmen.
     */
    @Test
    public void testConsencusLetsEveryVoterVoteAndIgnoresNullVoters() {
        Voter voterMock = mock(Voter.class);

        voters.add(new TrueVoter());
        voters.add(null);
        voters.add(new FalseVoter());
        voters.add(voterMock);
        voters.add(null);

        assertFalse(consensus.voting(voters));

        verify(voterMock).vote();
    }

    /**
     * Specificatie 5: Geen stemgerechtigde mag vaker dan 1 keer stemmen.
     */
    @Test
    public void testVoteIsCalledExactlyOnceOnVoters() {
        Voter voterMockFalse = mock(Voter.class),
              voterMockTrue = mock(Voter.class);

        when(voterMockFalse.vote()).thenReturn(false);
        when(voterMockTrue.vote()).thenReturn(true);

        voters.add(voterMockFalse);
        voters.add(voterMockTrue);

        consensus.voting(voters);

        verify(voterMockFalse, times(1)).vote();
        verify(voterMockTrue, times(1)).vote();
    }

    @Test
    public void testVoteIsCalledExactlyOnceOnDuplicateVoters() {
        Voter voterMockFalse = mock(Voter.class),
              voterMockTrue = mock(Voter.class);

        when(voterMockFalse.vote()).thenReturn(false);
        when(voterMockTrue.vote()).thenReturn(true);

        voters.add(voterMockFalse);
        voters.add(voterMockTrue);
        voters.add(voterMockFalse);
        voters.add(voterMockTrue);

        assertFalse(consensus.voting(voters));

        verify(voterMockFalse, times(1)).vote();
        verify(voterMockTrue, times(1)).vote();
    }

    @After
    public void tearDown() throws Exception {
        voters = null;
        consensus = null;
    }
}