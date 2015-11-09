package itest.consensus;

import java.util.ArrayList;

public interface Consensus {
    boolean voting(ArrayList<Voter> voters);
}
