package automata;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class DFAs {
    public static <A> DFA<A> complement(DFA<A> dfa) {
        Set<A> acceptStates = new HashSet<>(dfa.states());
        acceptStates.removeAll(dfa.acceptStates());
        return new DFA<>(dfa.states(), dfa.alphabet(), dfa.transitionFunction(), dfa.startState(), acceptStates);
    }

    static <A, B> DFA<Pair<A, B>> union(DFA<A> dfa1, DFA<B> dfa2) {
        Set<Pair<A, B>> states = new HashSet<>();

        for (A state1 : dfa1.states()) {
            for (B state2 : dfa2.states()) {
                states.add(new Pair<>(state1, state2));
            }
        }

        assert dfa1.alphabet().equals(dfa2.alphabet());

        Function<Pair<Pair<A, B>, Character>, Pair<A, B>> transitionFunction = pair -> {
            Pair<A, B> state = pair.first();
            char symbol = pair.second();
            return new Pair<>(dfa1.transitionFunction().apply(new Pair<>(state.first(), symbol)), dfa2.transitionFunction().apply(new Pair<>(state.second(), symbol)));
        };

        Pair<A, B> startState = new Pair<>(dfa1.startState(), dfa2.startState());

        Set<Pair<A, B>> acceptStates = new HashSet<>();

        for (Pair<A, B> state : states) {
            if (dfa1.acceptStates().contains(state.first()) || dfa2.acceptStates().contains(state.second())) {
                acceptStates.add(state);
            }
        }

        return new DFA<>(states, dfa1.alphabet(), transitionFunction, startState, acceptStates);
    }

    static <A, B> DFA<Pair<A, B>> intersection(DFA<A> dfa1, DFA<B> dfa2) {
        Set<Pair<A, B>> states = new HashSet<>();

        for (A state1 : dfa1.states()) {
            for (B state2 : dfa2.states()) {
                states.add(new Pair<>(state1, state2));
            }
        }

        assert dfa1.alphabet().equals(dfa2.alphabet());

        Function<Pair<Pair<A, B>, Character>, Pair<A, B>> transitionFunction = pair -> {
            Pair<A, B> state = pair.first();
            char symbol = pair.second();
            return new Pair<>(dfa1.transitionFunction().apply(new Pair<>(state.first(), symbol)), dfa2.transitionFunction().apply(new Pair<>(state.second(), symbol)));
        };

        Pair<A, B> startState = new Pair<>(dfa1.startState(), dfa2.startState());

        Set<Pair<A, B>> acceptStates = new HashSet<>();

        for (Pair<A, B> state : states) {
            if (dfa1.acceptStates().contains(state.first()) && dfa2.acceptStates().contains(state.second())) {
                acceptStates.add(state);
            }
        }

        return new DFA<>(states, dfa1.alphabet(), transitionFunction, startState, acceptStates);
    }
}