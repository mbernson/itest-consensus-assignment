package itest.consensus;

import java.util.ArrayList;

public interface Concensus {
    boolean voting(ArrayList<Voter> voters);
}
